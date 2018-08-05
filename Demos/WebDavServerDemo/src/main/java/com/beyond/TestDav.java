package com.beyond;

import org.apache.jackrabbit.commons.JcrUtils;

import javax.jcr.*;

public class TestDav {

    public static void main(String[] args) throws Exception {
        Repository repository = JcrUtils.getRepository();
        Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        try {
            String userID = session.getUserID();
            String descriptor = repository.getDescriptor(Repository.REP_NAME_DESC);
            System.out.println("userId:" + userID + "   descriptor:" + descriptor);

            //save
            Node rootNode = session.getRootNode();
//            Node hello = rootNode.addNode("hello");
//            Node world = hello.addNode("world");
//            world.setProperty("message","yes");
//            session.save();

            //retrieve
            Node node = rootNode.getNode("hello/world");
            Property message = node.getProperty("message");
            System.out.println(message.getString());

            rootNode.getNode("hello").remove();
            session.save();
        } finally {
            session.logout();
        }
    }
}
