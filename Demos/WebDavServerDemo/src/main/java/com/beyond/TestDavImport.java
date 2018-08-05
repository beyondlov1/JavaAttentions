package com.beyond;

import org.apache.jackrabbit.commons.JcrUtils;

import javax.jcr.*;
import java.io.FileInputStream;

public class TestDavImport {

    public static void main(String[] args) throws Exception {
        Repository repository = JcrUtils.getRepository();
        Session session = repository.login(new SimpleCredentials("admin", "admin".toCharArray()));
        try {
            String userID = session.getUserID();
            String descriptor = repository.getDescriptor(Repository.REP_NAME_DESC);
            System.out.println("userId:" + userID + "   descriptor:" + descriptor);

            //import
            Node rootNode = session.getRootNode();
            if (!rootNode.hasNode("importxml")){
                Node node = rootNode.addNode("importxml","nt:unstructured");
                FileInputStream fileInputStream = new FileInputStream("D:\\JavaProject\\TestWebDav\\src\\main\\resources\\test.xml");
                session.importXML(node.getPath(),fileInputStream,ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
                fileInputStream.close();
                session.save();
            }

            //retrieve
            dump(rootNode);

        } finally {
            session.logout();
        }
    }

    private static void dump(Node rootNode) throws RepositoryException{
        System.out.println(rootNode.getPath());
        if (rootNode.getName().equals("jcr:system")){
            return;
        }

        PropertyIterator properties = rootNode.getProperties();
        while (properties.hasNext()){
            Property property = properties.nextProperty();
            if (property.getDefinition().isMultiple()){
                Value[] values = property.getValues();
                for (Value value:values){
                    System.out.println(value.getString());
                }
            }else{
                System.out.println(property.getString());
            }
        }

        NodeIterator nodes = rootNode.getNodes();
        while (nodes.hasNext()){
            dump(nodes.nextNode());
        }
    }


}
