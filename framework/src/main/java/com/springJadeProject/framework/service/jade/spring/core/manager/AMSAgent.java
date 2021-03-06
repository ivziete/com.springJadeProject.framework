package com.springJadeProject.framework.service.jade.spring.core.manager;

import com.springJadeProject.framework.service.jade.spring.core.agent.AgentInterface;
import com.springJadeProject.framework.service.jade.spring.core.agent.SpringAgent;
import jade.core.AID;
import jade.domain.AMSService;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Reyes
 */


@Service
public class AMSAgent extends SpringAgent implements AgentInterface {
    private static final String AMS_LOCAL_NAME = "ams";
    private static final String DF_LOCAL_NAME = "df";
    private static final String RMA_LOCAL_NAME = "rma";

    @Override
    protected void setup()
    {
        super.setup();
        System.out.println("The agent " + getAID().getName() + " has been initiated.");
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("---->AMS Agent: getAID.getName --> " + this.getAID().getName() + " terminating");
    }

    @PostConstruct
    public void startup(){
        if (!isInitiated()){
            this.init();
        }
    }

    public List<AMSAgentDescription> getActiveAgentList(boolean allAgents) {
        AMSAgentDescription [] agents = {};
        List<AMSAgentDescription> customAgents = new ArrayList<>();
        try
        {
            SearchConstraints restrictions = new SearchConstraints();
            restrictions.setMaxResults ( -1L); /// All of them
            //new AMSAgentDescription because we don't need any concrete value for any field of AgentDescription
            agents = AMSService.search(this, new AMSAgentDescription(), restrictions);
        }
        catch (Exception e) { System.out.println(e); }


        for (int i=0; i < agents.length; i++)
        {
            AID id = agents[i].getName();
            String agentLocalName = id.getLocalName();
            if (allAgents ||(!agentLocalName.equals(AMS_LOCAL_NAME) && !agentLocalName.equals(DF_LOCAL_NAME) && !agentLocalName.equals(RMA_LOCAL_NAME))){
                customAgents.add(agents[i]);
            }
        }
        return customAgents;
    }

    public AMSAgentDescription getActiveAgentByLocalName(String localName){
        List<AMSAgentDescription> activeAgentList = getActiveAgentList(false);

        for (int i=0; i<activeAgentList.size(); i++){
            if(activeAgentList.get(i).getName().getLocalName().equals(localName)){
                return activeAgentList.get(i);
            }
        }
        return null;
    }

    @Override
    public AgentInterface getNewInstance() {
        return new AMSAgent();
    }


}

