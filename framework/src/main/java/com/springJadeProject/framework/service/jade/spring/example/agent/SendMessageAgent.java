package com.springJadeProject.framework.service.jade.spring.example.agent;

import com.springJadeProject.framework.service.jade.spring.core.agent.AgentInterface;
import com.springJadeProject.framework.service.jade.spring.core.agent.SpringAgent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alejandro Reyes
 */
//@Stateless @AgentQualifier(type=AgentType.SEND_MESSAGE_AGENT)
@Service
@Qualifier("SendMessageAgent")
public class SendMessageAgent extends SpringAgent implements AgentInterface {

    @Override
    protected void setup() {
        super.setup();
        System.out.println ("------>SendMessageAgent is on setup");
        System.out.println ("My name is " + this.getLocalName());
    }

    @Override
    protected void takeDown() {
        super.takeDown();
        System.out.println("---->SendMessageAgent is on takeDown: terminating");
    }

    @Override
    public AgentInterface getNewInstance() {
        return new SendMessageAgent();
    }



}
