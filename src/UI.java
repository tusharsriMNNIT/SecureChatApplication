


import java.awt.Frame;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.Socket;
import java.security.Key;
import java.security.*;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
public class UI extends javax.swing.JFrame {
   String name;
    private final Socket readSocket;
     static long takenTimeE,takenTimeD,timeTakenFile=0;
    private static int flag;
    private final Socket writeSocket;
    private ObjectOutputStream oos,fileoos;
    private ObjectInputStream ois,fileois;
    private PublicKey publicKeyServer;
    private PrivateKey keyRSAPrivate;
    private static Socket socket;
    public final static String UPDATE_USERS = "updateuserslist:";
    public static String sessionUsername = null;
    private Key AESKey,DESKey;
    static int val=1;
     File file;
     String fileName1="C:\\Users\\hp\\Desktop\\plain.txt";
    public UI(Socket readSocket,Socket writeSocket,final Key AESKey,final Key DESKey,final ObjectInputStream  ois,ObjectOutputStream oos,Socket fileSendSocket,String t) throws Exception {
        
        initComponents();
        
        this.readSocket = readSocket;
        this.writeSocket = writeSocket;
        this.AESKey = AESKey;
        this.DESKey = DESKey;
        fileoos = new ObjectOutputStream(fileSendSocket.getOutputStream());
        fileois = new ObjectInputStream(fileSendSocket.getInputStream());
        this.name=t;
        this.oos=oos;
        this.ois=ois;
        new Thread()
        {
            public void run()
            {
                long timeFile=0;
                while(true)
                {
                    try{
                    Message encryptedMessage= (Message) fileois.readObject();
                        String type = encryptedMessage.getType();
                        MsgDecryption mess=null;
                        if(type.equals("AES")){
                            {
                                mess = new MsgDecryption(encryptedMessage.getMessage(),AESKey,type);
                              //  consoleTextArea.append("Decryption Time(AES): "+takenTimeD+"\n");
                            }
                        }else{
                            mess = new MsgDecryption(encryptedMessage.getMessage(),DESKey,type);
                           // consoleTextArea.append("Decryption Time(DES): "+takenTimeD+"\n");
                        }
                        String plainMessageString = mess.getMessage();
                        chatBoxTextArea.append(" "+plainMessageString+"\n");
                       
                            consoleTextArea.setText("File Recieved\n");
                            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName1, true));
                            //writer.append(' ');
                            writer.append(plainMessageString+"\n");
                            writer.close();
                            //flag=0;
                        
                    }
                    catch(Exception e)
                    {
                        System.out.println(e);
                    }
                }
            }
        }.start();
        new Thread(){
            public void run(){
                try {
                    while(true){
                        Message encryptedMessage= (Message) ois.readObject();
                        String type = encryptedMessage.getType();
                        MsgDecryption mess=null;
                        if(type.equals("AES")){
                            {
                                mess = new MsgDecryption(encryptedMessage.getMessage(),AESKey,type);
                                consoleTextArea.append("Decryption Time(AES): "+takenTimeD+"\n");
                            }
                        }else{
                            mess = new MsgDecryption(encryptedMessage.getMessage(),DESKey,type);
                            consoleTextArea.append("Decryption Time(DES): "+takenTimeD+"\n");
                        }
                        String plainMessageString = mess.getMessage();
                        System.out.println(plainMessageString + " FROM using "+encryptedMessage.getType());
                        chatBoxTextArea.append("*****"+plainMessageString+"\n");
                    }
                    
                } catch (Exception ex) {
                    Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
        
        

    }
//    public void AesMessage() throws FileNotFoundException, IOException
//    {
//         JFileChooser chooser = new JFileChooser();
//        chooser.showOpenDialog(null);
//        file = chooser.getSelectedFile();
//        String fileName = file.getAbsolutePath();
//        System.out.println(fileName);
//        String msg;
//        BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
//        
//        while((msg=br.readLine())!=null)
//        {
//            
//        }
//        
//    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        msgTextField = new javax.swing.JTextField();
        onDesClick = new javax.swing.JButton();
        onAESClick = new javax.swing.JButton();
        graphButton = new javax.swing.JButton();
        onDESClickFile = new javax.swing.JButton();
        onAESClickFile = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        chatBoxTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        consoleTextArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 13, 64));

        headerLabel.setBackground(new java.awt.Color(30, 74, 142));
        headerLabel.setFont(new java.awt.Font("Arial", 1, 36)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(255, 255, 255));
        headerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        headerLabel.setText("Secret Chat");
        headerLabel.setOpaque(true);

        jPanel4.setBackground(new java.awt.Color(222, 163, 72));

        msgTextField.setFont(new java.awt.Font("Abyssinica SIL", 3, 24)); // NOI18N
        msgTextField.setForeground(new java.awt.Color(24, 118, 183));
        msgTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                msgTextFieldActionPerformed(evt);
            }
        });

        onDesClick.setBackground(new java.awt.Color(40, 116, 240));
        onDesClick.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        onDesClick.setForeground(new java.awt.Color(38, 254, 60));
        onDesClick.setText("Using DES");
        onDesClick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDesClickActionPerformed(evt);
            }
        });

        onAESClick.setBackground(new java.awt.Color(40, 116, 240));
        onAESClick.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        onAESClick.setForeground(new java.awt.Color(50, 255, 0));
        onAESClick.setText("Using AES");
        onAESClick.setMaximumSize(new java.awt.Dimension(95, 29));
        onAESClick.setMinimumSize(new java.awt.Dimension(95, 29));
        onAESClick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAESClickActionPerformed(evt);
            }
        });

        graphButton.setBackground(new java.awt.Color(40, 116, 240));
        graphButton.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        graphButton.setForeground(new java.awt.Color(32, 255, 0));
        graphButton.setText("Comparison AES and DES");
        graphButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                graphButtonActionPerformed(evt);
            }
        });

        onDESClickFile.setBackground(new java.awt.Color(40, 116, 240));
        onDESClickFile.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        onDESClickFile.setForeground(new java.awt.Color(32, 255, 0));
        onDESClickFile.setText("File Using DES");
        onDESClickFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onDESClickFileActionPerformed(evt);
            }
        });

        onAESClickFile.setBackground(new java.awt.Color(40, 116, 240));
        onAESClickFile.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        onAESClickFile.setForeground(new java.awt.Color(32, 255, 0));
        onAESClickFile.setText("File Using AES");
        onAESClickFile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                onAESClickFileActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(60, 112, 147));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 73, 255));
        jLabel1.setText("Enter Text To Send");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(msgTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(onDESClickFile, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(onAESClickFile, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(onDesClick, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(graphButton, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(onAESClick, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(msgTextField)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addComponent(graphButton, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(onDESClickFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(onAESClickFile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(onDesClick, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(onAESClick, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 112, Short.MAX_VALUE)))
                        .addContainerGap())))
        );

        jPanel3.setBackground(new java.awt.Color(13, 105, 121));

        chatBoxTextArea.setEditable(false);
        chatBoxTextArea.setColumns(20);
        chatBoxTextArea.setFont(new java.awt.Font("Abyssinica SIL", 3, 18)); // NOI18N
        chatBoxTextArea.setRows(5);
        chatBoxTextArea.setMaximumSize(new java.awt.Dimension(50, 50));
        jScrollPane1.setViewportView(chatBoxTextArea);

        consoleTextArea.setEditable(false);
        consoleTextArea.setColumns(20);
        consoleTextArea.setFont(new java.awt.Font("Abyssinica SIL", 2, 16)); // NOI18N
        consoleTextArea.setRows(5);
        jScrollPane2.setViewportView(consoleTextArea);

        jLabel2.setBackground(new java.awt.Color(78, 141, 167));
        jLabel2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 51, 0));
        jLabel2.setText("                         Info");

        jLabel4.setBackground(new java.awt.Color(78, 141, 167));
        jLabel4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 22, 0));
        jLabel4.setText("                          Messages");

        jButton1.setBackground(new java.awt.Color(212, 216, 45));
        jButton1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(226, 18, 16));
        jButton1.setText("Delete");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(headerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 816, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.LINE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void onDESClickFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDESClickFileActionPerformed
       
        
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                long timeDES=0;
                String msgStr;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
               // chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MsgEncryption messEncryption = new MsgEncryption(msgStr,DESKey,"DES");
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"DES"); 
                    timeDES += takenTimeE;
                    //chatBoxTextArea.append(msgStr+" \n");
                    fileoos.writeObject(encryptedMsg);
                   
                    //Launcher.flag=1;
               
                }
                consoleTextArea.append("Encryption time File(DES): "+timeDES);
               /// timeTakenFile = 12354+timeDES;
               if(name.equals("server"))
                 chatBoxTextArea.append("   Client1: "+ fileName+"has been sent\n");
               else
                  chatBoxTextArea.append("   Client2: "+ fileName+"has been sent\n");
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
       UI.flag=0;     
    }//GEN-LAST:event_onDESClickFileActionPerformed

    private void msgTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_msgTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_msgTextFieldActionPerformed

    private void onAESClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAESClickActionPerformed

        try {
            
            String msgStr = msgTextField.getText();
              msgTextField.setText("");
              if(name.equals("server"))
            chatBoxTextArea.append("   Client1: "+msgStr+"\n");
              else
                  chatBoxTextArea.append("    Client2: "+msgStr+"\n");
            MsgEncryption messEncryption = new MsgEncryption(msgStr,AESKey,"AES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"AES");
            oos.writeObject(encryptedMsg);
            System.out.println("mes sent");
            //oos.writeInt(0);
             consoleTextArea.append("Encryption Time(AES): "+takenTimeE+"\n");
             consoleTextArea.append("Encrypted Msg(AES): "+encryptedMsgStr+"\n");
            System.out.println("int sent");

        } catch (Exception ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_onAESClickActionPerformed

    private void onDesClickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onDesClickActionPerformed
        try {
            String msgStr = msgTextField.getText();
            msgTextField.setText("");
            if(name.equals("server"))
            chatBoxTextArea.append("    Client1: "+msgStr+"\n");
            else
                 chatBoxTextArea.append("    Client2: "+msgStr+"\n");
            MsgEncryption messEncryption = new MsgEncryption(msgStr,DESKey,"DES");
            String encryptedMsgStr = messEncryption.getMessageString();
            Message encryptedMsg = new Message(encryptedMsgStr,"DES");
             consoleTextArea.append("Encryption Time(DES): "+takenTimeE+"\n");
             consoleTextArea.append("Encrypted Msg(DES): "+encryptedMsgStr+"\n");
            oos.writeObject(encryptedMsg);
            
           // oos.writeInt(0);
        } catch (Exception ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_onDesClickActionPerformed

    private void graphButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_graphButtonActionPerformed
       
            Frame f = new Frame();
        f.setBounds(200,200,1000,800);
        f.add(new Graph());
        f.setResizable(false);
        f.setVisible(true);
        Frame f1 = new Frame();
        f1.setBounds(200,200,1000,800);
        f1.add(new Des_Graph());
        f1.setResizable(false);
        f1.setVisible(true);
        
        
        /*try {
            sleep(5000);
            f.dispose();
        } catch (InterruptedException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }//GEN-LAST:event_graphButtonActionPerformed

    private void onAESClickFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_onAESClickFileActionPerformed
       
        //String fileName=fileName.replace("\" , '/');
        JFileChooser chooser = new JFileChooser();
        int values1 = chooser.showOpenDialog(null);
        file = chooser.getSelectedFile();
        val=1;
        if (values1 == JFileChooser.APPROVE_OPTION)
        {
            try {
                 
                System.out.println(file.getPath());
                String fileName = file.getAbsolutePath();
                System.out.println(fileName);
                String msgStr;
                long timeForFile=0;
                BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
               // chatBoxTextArea.append("file: "+fileName+" has been sent to another client at time "+new Date());
                while((msgStr=br.readLine())!=null)
                {

                    MsgEncryption messEncryption = new MsgEncryption(msgStr,AESKey,"AES");
                    timeForFile += takenTimeE;
                    String encryptedMsgStr = messEncryption.getMessageString();
                    Message encryptedMsg = new Message(encryptedMsgStr,"AES"); 
                 //   chatBoxTextArea.append(msgStr+" \n");
                    //Launcher.flag=1;
                    fileoos.writeObject(encryptedMsg);
                     
                    
                  // oos.writeInt(1);
                   // System.out.println("int sent");
                }
                consoleTextArea.append("Encryption time File(AES): "+timeForFile);
                if(name.equals("server"))
                chatBoxTextArea.append("   Client1: "+ fileName+"has been sent\n");
                else
                    chatBoxTextArea.append("   Client2: "+ fileName+"has been sent\n");
                //timeTakenFile = 12354+timeForFile;
            } catch (Exception ex) {
                Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
            }    
        } else if (values1 == JFileChooser.CANCEL_OPTION) {
            System.out.println("No file is selected");
        } else if (values1 == JFileChooser.ERROR_OPTION) {
            System.out.println("Error!");
        } else if (file == null) {
            System.out.println("No File is chosen");
        }
        UI.flag=0;     
    }//GEN-LAST:event_onAESClickFileActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        consoleTextArea.setText("");
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea chatBoxTextArea;
    private javax.swing.JTextArea consoleTextArea;
    private javax.swing.JButton graphButton;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField msgTextField;
    private javax.swing.JButton onAESClick;
    private javax.swing.JButton onAESClickFile;
    private javax.swing.JButton onDESClickFile;
    private javax.swing.JButton onDesClick;
    // End of variables declaration//GEN-END:variables
}
