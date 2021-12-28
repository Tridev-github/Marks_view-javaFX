package com.example.demo;

import java.sql.*;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;

public class HelloApplication extends Application {
    int res;
    int click=0;
    @Override
    public void start(Stage stage) throws IOException {
        BackgroundFill bg111 =new BackgroundFill(Color.rgb(186,219,255),new CornerRadii(1),null);
        BackgroundFill bg =new BackgroundFill(Color.ALICEBLUE,new CornerRadii(1),null);
        BackgroundFill bg1 =new BackgroundFill(Color.rgb(255,170,0),new CornerRadii(1),null);
        BackgroundFill bg11 =new BackgroundFill(Color.rgb(100,255,170),new CornerRadii(1),null);
        Font f = new Font("Helvetica",30);

        Image up = new Image(new FileInputStream("src/blob (4).jpg"));
        ImageView upf =new ImageView(up);
        Button update = new Button();
        update.setGraphic(upf);
        Label cli = new Label();

        Label lin1 = new Label("Give ID, subject and marks of students");
        lin1.setFont(f);
        Text id = new Text( "ID      : ");
        id.setFont(f);
        Text sub = new Text("Subject : ");
        sub.setFont(f);
        Text mar = new Text("Marks   : ");
        mar.setFont(f);

        TextField idf = new TextField();
        TextField subf = new TextField();
        TextField marf = new TextField();

        GridPane ggg = new GridPane();
        ggg.setBackground(new Background(bg111));
        ggg.add(lin1,1,0);
        ggg.add(id,0,1);
        ggg.add(sub,0,2);
        ggg.add(mar,0,3);
        ggg.add(idf,1,1);
        ggg.add(subf,1,2);
        ggg.add(marf,1,3);
        ggg.add(update,0,6);

        Label lin = new Label("ID references for students");
        lin.setFont(new Font("Helvetica",30));
        Text text = new Text("Welcome to teacher's view");
        text.setFont(new Font("Helvetica",30));
        VBox vbl = new VBox();
        vbl.setBackground(new Background(bg1));
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con2= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","1234");


            PreparedStatement stmt2=con2.prepareStatement("select * from main where role='Stu'");
            ResultSet i2=stmt2.executeQuery();

            vbl.getChildren().add(lin);

            while(i2.next()) {
                Text st = new Text("ID:  "+i2.getString(1)+"  Name:  "+i2.getString(2).toString());
                st.setFont(new Font("Helvetica",20));
                vbl.getChildren().add(st);
            }

            con2.close();
        }catch(Exception e){ System.out.println(e);}
        update.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con3= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","1234");

                    PreparedStatement statement1 =  con3.prepareStatement("insert into student values(?,?,?)");
                    statement1.setString(1,idf.getText().toString());
                    statement1.setString(2,subf.getText().toString());
                    statement1.setString(3,marf.getText().toString());
                    statement1.executeUpdate();
                    click++;

                    cli.setText("Updated "+click+" Marks");
                    cli.setTextFill(Color.GREEN);
                    cli.setFont(f);
                    ggg.add(cli,0,4);
                    con3.close();
                }catch(Exception e){ System.out.println(e);}
            }
        });

        BorderPane bpp1 = new BorderPane();
        bpp1.setTop(text);
        BorderPane.setAlignment(text, Pos.TOP_CENTER);
        BorderPane.setMargin(text,new Insets(10));
        bpp1.setCenter(ggg);
        BorderPane.setAlignment(ggg, Pos.CENTER);
        bpp1.setRight(vbl);
        bpp1.setBackground(new Background(bg111));

        Scene tea = new Scene(bpp1, 1530, 800);
        VBox v11 = new VBox();
        Text st11 = new Text("SUBJECT       MARKS");
        st11.setFont(new Font("Helvetica",40));
        st11.setFill(Color.BLACK);
        v11.getChildren().add(st11);

        Button bfin =new Button();
        Image imff = new Image(new FileInputStream("src/blob (5).jpg"));
        ImageView imfff = new ImageView(imff);
        bfin.setGraphic(imfff);
        bfin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con4= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","1234");

                    PreparedStatement statement2 =  con4.prepareStatement("select subject,marks from student where sid=?");
                    String rest = ""+res;
                    statement2.setString(1,rest);
                    ResultSet rs = statement2.executeQuery();


                    while(rs.next()) {

                        Text st111 = new Text(rs.getString(1)+"        "+rs.getString(2).toString());
                        st111.setFont(new Font("Helvetica",40));
                        st111.setFill(Color.GREEN);
                        v11.getChildren().add(st111);
                    }
                    con4.close();
                }catch(Exception e){ System.out.println(e);}
            }
        });
        Text textf = new Text("Welcome to Student's view");
        textf.setFont(new Font("Helvetica",50));
        BorderPane bpp = new BorderPane();
        BorderPane.setAlignment(st11,Pos.TOP_LEFT);
        bpp.setLeft(v11);
        bpp.setTop(textf);
        BorderPane.setAlignment(textf,Pos.TOP_CENTER);
        v11.setBackground(new Background(bg111));
        bpp.setBottom(bfin);
        Scene stu = new Scene(bpp, 1530, 800);

        Label l1 = new Label("Welcome to VIT marks portal");
        l1.setFont(new Font("Arial",30));

        Label l2 = new Label("Welcome to VIT marks portal");
        l2.setFont(new Font("Arial",30));


        TextField t1 = new TextField();
        PasswordField p1 =new PasswordField();
        Text un = new Text("Username:");
        Text un1 = new Text("Password:");
        GridPane gp1 = new GridPane();
        gp1.add(t1,3,1);
        gp1.add(p1,3,2);
        gp1.add(un,0,1);
        gp1.add(un1,0,2);

        Button loginf = new Button();
        loginf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","1234");


                    PreparedStatement stmt=con.prepareStatement("select * from main where name = ?");
                    stmt.setString(1, t1.getText().toString());

                    ResultSet i=stmt.executeQuery();
                    int check=0;
                    Text tlt = new Text();
                    tlt.setFill(Color.RED);
                    while(i.next()) {
                        if((i.getString(3).toString()).equals((p1.getText().toString()))) {
                            check = 1;
                            res = i.getInt(1);
                            break;
                        }
                    }
                    if(check==1){
                        if((i.getString(4).toString()).equals("Stu")) {
                            stage.setScene(stu);
                        }
                        else{
                            stage.setScene(tea);
                        }
                    }
                    else{
                        tlt.setText("Check your Username and Password");
                    }
                    gp1.add(tlt,0,3);
                    con.close();
                }catch(Exception e){ System.out.println(e);}
            }
        });
        Image lo = new Image(new FileInputStream("src/blob.jpg"));
        ImageView im = new ImageView(lo);
        loginf.setGraphic(im);
        Label ll = new Label("Login");
        ll.setFont(new Font("Helvetica",30));

        BorderPane bplogin = new BorderPane();
        bplogin.setTop(ll);
        bplogin.setCenter(gp1);
        bplogin.setBottom(loginf);


        bplogin.setBackground(new Background(bg));

        Scene loginsc = new Scene(bplogin, 300, 200);


        TextField t2 = new TextField();
        PasswordField p2 =new PasswordField();
        PasswordField p3 =new PasswordField();
        Text un0 = new Text("Username:");
        Text un2 = new Text("Password:");
        Text un20 = new Text("Confirm Password:");

        RadioButton rb1 = new RadioButton("Teacher");
        RadioButton rb2 = new RadioButton("Student");
        final ToggleGroup tg = new ToggleGroup();
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        rb2.setSelected(true);

        GridPane gp2 = new GridPane();
        gp2.add(t2,3,1);
        gp2.add(p2,3,2);
        gp2.add(un0,0,1);
        gp2.add(un2,0,2);
        gp2.add(un20,0,3);
        gp2.add(p3,3,3);
        gp2.add(rb1,0,4);
        gp2.add(rb2,3,4);
        gp2.setHgap(2);
        gp2.setVgap(2);

        Button logupf = new Button();
        logupf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con1= DriverManager.getConnection("jdbc:mysql://localhost:3306/temp","root","1234");


                    PreparedStatement stmt1=con1.prepareStatement(" insert into main(name,password,role) values(?,?,?)");
                    stmt1.setString(1, t2.getText().toString());
                    stmt1.setString(2, p2.getText().toString());
                    if(rb1.isSelected()){
                        stmt1.setString(3,"Tea");
                    }
                    else {
                        stmt1.setString(3,"Stu");
                    }
                    if((p2.getText().toString()).equals((p3.getText().toString()))){

                        stmt1.executeUpdate();
                        Text tlt3 = new Text();
                        tlt3.setFill(Color.GREEN);
                        tlt3.setText("Account created");
                        gp2.add(tlt3,5,5);
                    }
                    else{
                        Text tlt2 = new Text();
                        tlt2.setFill(Color.RED);
                        tlt2.setText("Your confirmed Password doesn't match");
                        gp2.add(tlt2,0,5);
                    }
                    con1.close();
                }catch(Exception e){ System.out.println(e);}
            }
        });
        Image lof = new Image(new FileInputStream("src/blob (3).jpg"));
        ImageView imf = new ImageView(lof);
        logupf.setGraphic(imf);
        Label ll2 = new Label("Sign Up");
        ll2.setFont(new Font("Helvetica",30));
        ll2.setTextFill(Color.BLACK);

        BackgroundFill bg2 =new BackgroundFill(Color.ALICEBLUE,new CornerRadii(1),null);

        BorderPane bpsign = new BorderPane();
        bpsign.setTop(ll2);
        bpsign.setCenter(gp2);
        bpsign.setLeft(null);
        bpsign.setRight(null);
        bpsign.setBottom(logupf);
        bpsign.setBackground(new Background(bg2));
        bpsign.setPadding(new Insets(5));
        BorderPane.setMargin(ll2,new Insets(10));
        Scene signsc = new Scene(bpsign, 400, 300);

        Label l = new Label("Welcome to VIT marks portal");
        l.setFont(new Font("Helvetica",30));

        Button login = new Button();
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(loginsc);
            }
        });

        Button signup = new Button();
        signup.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                stage.setScene(signsc);
            }
        });

        Image log = new Image(new FileInputStream("src/blob (1).jpg"));
        ImageView logv = new ImageView(log);
        logv.setFitHeight(70);
        logv.setFitWidth(350);
        Image sig = new Image(new FileInputStream("src/blob (2).jpg"));
        ImageView sigv = new ImageView(sig);
        sigv.setFitHeight(70);
        sigv.setFitWidth(256);
        GridPane gp = new GridPane();
        login.setGraphic(logv);
        signup.setGraphic(sigv);

        gp.add(login,0,2,1,1);
        gp.add(signup,2,2,1,1);
        gp.setVgap(50);
        gp.setHgap(450);


        Image imr = new Image(new FileInputStream("src/124f55209dacad4cffd468e2997e1dec.jpg"));
        ImageView imvr = new ImageView(imr);
        StackPane sip = new StackPane();
        sip.autosize();
        sip.getChildren().add(imvr);

        BorderPane bp = new BorderPane();
        bp.setTop(sip);
        bp.setBottom(gp);
        bp.setCenter(l);
        bp.setBackground(new Background(bg111));

        Scene scene = new Scene(bp, 1530, 800);

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}