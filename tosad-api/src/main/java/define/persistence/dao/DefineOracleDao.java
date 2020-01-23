package define.persistence.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import define.business.domain.LiteralValue;
import define.business.domain.Operator;
import define.business.domain.TableAttribute;
import define.business.domain.Trigger;
import define.business.domain.businessrules.BusinessRule;

public class DefineOracleDao implements DefineDao {
	private BaseDao dbconnection;
	
	public DefineOracleDao(BaseDao dbconnection) {
		this.dbconnection = dbconnection;
	}

	@Override
	public HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> getAvailableInput() {
        String query = "select operator.name as \"operator\", businessruletype.code as \"businessrulecode\", businessruletype.name as \"businessrulename\", businessruletype.description as \"businessruledescription\", category.name as \"categoryname\" from operator, businessruletype, category, operatorrule where operator.id = operatorrule.id and operatorrule.code = businessruletype.code and businessruletype.categoryid = category.id order by businessruletype.name asc";
        HashMap<String, HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>> data = new HashMap<>();        
        
        try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultset = statement.executeQuery();
            
            data.put("categories", new HashMap<String, HashMap<String, HashMap<String, ArrayList<String>>>>());
            while(resultset.next()) {
            	if(data.get("categories").containsKey(resultset.getString("categoryname"))) {
            		
            	} else {
            		data.get("categories").put(resultset.getString("categoryname"), new HashMap<String, HashMap<String, ArrayList<String>>>());
            	}
            	if(data.get("categories").get(resultset.getString("categoryname")).containsKey(resultset.getString("businessrulename"))) {
            		
            	} else {
            		data.get("categories").get(resultset.getString("categoryname")).put(resultset.getString("businessrulename"), new HashMap<String, ArrayList<String>>());
            		data.get("categories").get(resultset.getString("categoryname")).get(resultset.getString("businessrulename")).put("operators", new ArrayList<String>());
            	}
            	data.get("categories").get(resultset.getString("categoryname")).get(resultset.getString("businessrulename")).get("operators").add(resultset.getString("operator"));
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
        return data;
    }
	
	public String defineRule(BusinessRule rule) {
		String successful = "failed";
		
		// check if target table/attribute is in our database. if not, it will be added.
		checkTargetData(rule);
			
		// start with saving the rule
		int triggerId = getTriggerFromRule(rule.getTrigger());
		int operatorId = getOperatorFromRule(rule.getOperator());
		int attributeId = getTableAttributeFromRule(rule.getTable().getSelectedTableAttribute());
		String ruletypecode = getRuletypeFromRule(rule.getType());
		
		// only works if trigger doesn't exist, will be checked in this method
		insertTrigger(rule.getTrigger(), triggerId); 
		triggerId = getTriggerFromRule(rule.getTrigger());
			
        try (Connection conn = dbconnection.getConnection()) {
        	if (ruletypecode.equals("ARNG") || ruletypecode.equals("ACMP") || ruletypecode.equals("ALIS") || ruletypecode.equals("AOTH")) {
        		// insert businessrule
	    		String businessruleinsert = "insert into businessrule (name, type, failure_message, attributeid, operatorid, triggerid) values (? || (businessrule_sequence.nextval + 1), ?, ?, ?, ?, ?)";
				PreparedStatement statement = conn.prepareStatement(businessruleinsert);
				
				statement.setString(1, rule.getName() + ruletypecode + "_");
				statement.setString(2, ruletypecode);
				statement.setString(3, rule.getTrigger().getFailuremessage());
				statement.setInt(4, attributeId);
				statement.setInt(5, operatorId);
				statement.setInt(6, triggerId);
				statement.executeUpdate();
				
				statement.close();
				
				// get businessrule id 
				String ruleidquery = "select max(id) as id from businessrule";
				int ruleid = 0;
				
				PreparedStatement getruleidstatement = conn.prepareStatement(ruleidquery);
				ResultSet result = getruleidstatement.executeQuery();
				while (result.next()) {
					ruleid = result.getInt("id");
				}
				getruleidstatement.close();
				result.close();
        		
				// insert parameters
        		String parameterinsert = "insert into parameter (value) values (?)"; 
        		
        		for (LiteralValue l : rule.getValues() ) {
        			PreparedStatement pstatement = conn.prepareStatement(parameterinsert);
        			pstatement.setString(1, l.getValue());
                    pstatement.executeQuery();
                    
                    pstatement.close();
                    
                    String paridquery = "select max(id) as id from parameter";
    				int parid = 0;
    				
    				PreparedStatement getparidstatement = conn.prepareStatement(paridquery);
    				ResultSet parresult = getparidstatement.executeQuery();
    				while (parresult.next()) {
    					parid = parresult.getInt("id");
    				}
    				
    				getparidstatement.close();
    				parresult.close();
  		
    				String coupletableinsert = "insert into parameterrule (businessruleid, parameterid) values (?, ?)";
    				PreparedStatement coupletableinsertstatement = conn.prepareStatement(coupletableinsert);
    				coupletableinsertstatement.setInt(1, ruleid);
    				coupletableinsertstatement.setInt(2, parid);
    				coupletableinsertstatement.executeUpdate();
        		}
        		
        		successful = "success";
			}
	   
//			switch (ruletypecode) {
//			case "TCMP":
//				break;
//			case "TOTH":
//				break;
//			case "ICMP":
//				break;
//			case "EOTH":
//				break;
//			case "MODI":
//				break;
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return successful;
	}
	
	private void checkTargetData(BusinessRule rule) {
		String checktargettable = "select * from targettable where name = ?";
		String inserttargettable = "insert into targettable (name, databaseid) values (?, 1)";
		boolean tableExists = false;
        try (Connection conn = dbconnection.getConnection()) {
        	
    		PreparedStatement statement = conn.prepareStatement(checktargettable);
    		statement.setString(1, rule.getTable().getName());
    		ResultSet result = statement.executeQuery();
    		
    		int size = 0;
    		while (result.next()) {
    			size++;
    		}
    		
    		if (size > 0) {
    			tableExists = true;
    		}
    		
    		if (!tableExists) {
        		PreparedStatement insertstatement = conn.prepareStatement(inserttargettable);
        		insertstatement.setString(1, rule.getTable().getName());
        		insertstatement.executeQuery();
        		insertstatement.close();
    		}
    		
    		statement.close();
    		result.close();
    		
    		String checktableattribute = "select * from targettableattribute where name = ?";
    		boolean tableattributeExists = false;   
    		
    		PreparedStatement tableattributestatement = conn.prepareStatement(checktableattribute);
    		tableattributestatement.setString(1, rule.getTable().getSelectedTableAttribute().getName());
    		ResultSet tableattributeresult = tableattributestatement.executeQuery(); 
    		
    		int sizez = 0;
    		while (tableattributeresult.next()) {
    			sizez++;
    		}
    		
    		if (sizez > 0) {
    			tableattributeExists = true;
    		}
    		
    		if (!tableattributeExists) {
    			PreparedStatement gettargettable = conn.prepareStatement(checktargettable);
    			gettargettable.setString(1, rule.getTable().getName());
        		ResultSet targettableresult = gettargettable.executeQuery();
        		
        		int tableid = 0;
    			while (targettableresult.next()) {
    				tableid = targettableresult.getInt("id");
    			}
    			
    			String inserttableattribute = "insert into targettableattribute (name, tableid) values (?, ?)";
    			PreparedStatement insertstatement = conn.prepareStatement(inserttableattribute);
    			insertstatement.setString(1, rule.getTable().getSelectedTableAttribute().getName());
    			insertstatement.setInt(2, tableid);
    			insertstatement.executeQuery();
    			
    			insertstatement.close();
    		}

        } catch (Exception e) {
        	e.printStackTrace();
        }

	}
	
	private int getOperatorFromRule(Operator operator) {
		String query = "SELECT * from operator o where o.name = ?";
        int operatorId = 0;
                
		try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, operator.getName());
            ResultSet resultset = statement.executeQuery();
            
            while (resultset.next()) {
            	operatorId = statement.getResultSet().getInt("id");
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();       
		return operatorId;
	}

	private int getTriggerFromRule(Trigger trigger) {
		String query = "SELECT * from generatedtrigger t where t.name = ?";
        int triggerid = 0;
                
		try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, trigger.getTriggercode());
            ResultSet resultset = statement.executeQuery();
            
            while (resultset.next()) {
            	triggerid = statement.getResultSet().getInt("id");
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
		return triggerid;
	}
	
	private int getTableAttributeFromRule(TableAttribute attribute) {
		String query = "SELECT * from targettableattribute t where t.name = ?";
        int attributeid = 0;
                
		try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, attribute.getName());
            ResultSet resultset = statement.executeQuery();
            
            while (resultset.next()) {
            	attributeid = statement.getResultSet().getInt("id");
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
		return attributeid;
	}
	
	private String getRuletypeFromRule(String type) {
		String query = "SELECT * from businessruletype b where b.name = ?";
        String ruletypecode = "";
                
		try (Connection conn = dbconnection.getConnection()) {

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, type);
            ResultSet resultset = statement.executeQuery();
            
            while (resultset.next()) {
            	ruletypecode = statement.getResultSet().getString("code");
            }
            resultset.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dbconnection.closeConnection();
		return ruletypecode;
	}
	
	// if trigger doesn't exist yet, use this method
	private void insertTrigger(Trigger trigger, int triggerid) {
		
		try (Connection conn = dbconnection.getConnection()) {	
			boolean triggerAdded = false;
			if (triggerid == 0) {
				
				String triggerquery = "insert into generatedtrigger (name, event) values (?, ?)";
				PreparedStatement triggerstatement = conn.prepareStatement(triggerquery);
				triggerstatement.setString(1, trigger.getTriggercode());
				triggerstatement.setString(2, trigger.getTriggerevent());
				
				triggerAdded = triggerstatement.executeUpdate() > 0;	
				System.out.println("Trigger inserted: " + triggerAdded);
			} else {
				System.out.println("Trigger inserted: " + triggerAdded + ", trigger already exists");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
