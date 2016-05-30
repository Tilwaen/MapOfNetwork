package cz.muni.fi.pb138.Validation;

import java.io.File;
import java.io.IOException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.xml.sax.SAXException;

/**
 * Validation of XML document.
 * @author PB138 study materials, "ValidatorDemo"
 */
public class Validator {
    private String schemaLanguage;
    private SchemaFactory factory;
    
    public Validator(String[] args) {
        long startTime = System.currentTimeMillis();

        Validator demo = new Validator(XMLConstants.W3C_XML_SCHEMA_NS_URI);

        File doc = new File(args[0]);
        File schema = null;
        if (args.length > 1) {
            schema = new File(args[1]);
        }

        if (schema == null) {
            System.out
                    .println("Validating '"
                            + doc
                            + "' against the schema specified in the instance document");
        } else {
            System.out.println("Validating '" + doc + "' against '" + schema
                    + "'");
        }
        try {
            if (schema == null) {
                demo.validate(doc);
            } else {
                demo.validate(doc, schema);
            }
            System.out.println("Document is valid.");

        } catch (SAXException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        
        System.out.println("Validation finished in " + (endTime-startTime) + " ms.");
    }
    
    public Validator(String sl) {
        // specify the schema language
        schemaLanguage = sl;
        // create a SchemaFactory capable of understanding WXS schemas
        factory = SchemaFactory.newInstance(schemaLanguage);
    }
    
    public void validate(File f, File schemaFile) throws SAXException,
            IOException {
        // load a WXS schema, represented by a Schema instance
        Schema schema = factory.newSchema(schemaFile);

        // create a Validator instance, which can be used to validate an instance document
        javax.xml.validation.Validator validator = schema.newValidator();

        // validate the document
        validator.validate(new StreamSource(f));
    }

    public void validate(File f) throws SAXException, IOException {
        
        // use schema referred in the instance document 
        Schema schema = factory.newSchema();

        // create a Validator instance, which can be used to validate an instance document
        javax.xml.validation.Validator validator = schema.newValidator();

        // validate the document
        validator.validate(new StreamSource(f));
    }
}
