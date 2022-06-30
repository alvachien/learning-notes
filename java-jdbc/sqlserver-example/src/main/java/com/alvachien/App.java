package com.alvachien;

// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileReader;
// import java.io.Reader;

import com.alvachien.util.ExceptionHandler;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello world");

        try{
            ProductsComponent comp = new ProductsComponent();
            if (comp.tryConnection()) {
                System.out.println("Demo: Try to connect with DriverManager");
                System.out.println("SUCCESS");
                System.out.println("Demo: Try to print out all lists");
                comp.printProductList(50.0d, 100.0d);
            } else {
                System.out.println("Demo: Try to connect with DriverManager");
                System.out.println("FALLED");
            }

            System.out.println("Try to update quanity in order");
            OrderComponent ordercmp = new OrderComponent();
            ordercmp.updateOrderQuantity(10138, "S24_2022", 88);            
            System.out.println("Quantity changed");
            // Check in DB with following SQL:
            // select * from orderdetails 
            //     where orderNumber = 10138
            //     and productCode = 'S24_2022'

            // Can replace only once.
            //
            // System.out.println("Try to replace a manager");
            HrComponent hrcomp = new HrComponent();
            // int count = hrcomp.replaceSalesManager("1143", "1621");
            // System.out.println(count + "employees have been reassigned");

            System.out.println("Add a new manager");
            int key = hrcomp.addEmployee("Williams", "Roger", "x104", "rwilliam@classicmodelcars.com",
                "3", "Sales Manager(NA)");
            System.out.println("The auto-generated primary key = " + key);
            hrcomp.deleteEmployee("1703");

            boolean delsucc = hrcomp.deleteEmployee(Integer.toString(key));
            System.out.println("Employee " + key + " has " + (delsucc ? "deleted" : "not been deleted"));

            System.out.println("Update file into DB");
            // Can only insert one.
            String fileName = "JanesAllWorldAircraft1913_704482.txt";
            // File file = new File(fileName);
            // FileReader fileReader = new FileReader(file);

            // boolean insertFileSuccess = comp.storeCLOB("Planes", fileReader);
            // fileReader.close();

            // For reading
            boolean insertFileSuccess = true;
            if (insertFileSuccess) {
                System.out.println("Success: the text contents of " + fileName + " has been uploaded");

                // Reader reader = comp.readCLOB("Planes");
                // int chr = 0;
                // while((chr = reader.read()) > 0) {
                //     System.out.write(chr);
                // }
                // reader.close();

                comp.readCLOB("Planes");
            } else {
                System.out.println("Fail: the text contents of " + fileName + " has NOT been uploaded");
            }

            // Store blob. - Only once.
            // String imgfileName = "bi-plane.png";
            // File imgfile = new File(imgfileName);
            // FileInputStream imgInputStream = new FileInputStream(imgfile);
            // boolean imgsuccess = comp.storeBLOB("Planes", imgInputStream);
            // imgInputStream.close();
            // if (imgsuccess) {
            //     System.out.println("Success: The image " + imgfileName + " has been stored");
            // } else {
            //     System.out.println("Faile: The image " + imgfileName + " has NOT been stored");
            // }

            System.out.println("List out all products by calling listProductsBy");
            comp.listProductsBy("Planes");

            System.out.println("Update email");
            String newEmail = "diane@classicmodelcars.com";
            String oldEmail = hrcomp.updateEmail(1002, newEmail);
            if (oldEmail != null) {
                System.out.println("email changed from " + oldEmail + " to " + newEmail);
            }

        } catch(Exception exp) {            
            ExceptionHandler.handleException(exp);
        }
    }
}
