/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class servicesuser {
        Connection c= ConnexionBD.getInstance().getCnx();

    public String getimage (String mail)
      { String image="";
          try {
            String stat = ("select image from user  where email='"+mail+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            if (rs.next()) {
                image=rs.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
      }
         public void activercompte(String mail)
    {
            try {
                PreparedStatement pt = c.prepareStatement("update user set active=1 where email=?  ");
                pt.setString(1,mail);
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }
             public void desactivercompte(String mail, String mdp)
    {
       
            try {
                PreparedStatement pt = c.prepareStatement("update user set active=0 where email=? and mdp=? ");
                pt.setString(1,mail);
                pt.setString(2,mdp);
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }
     public void ajouterpdp (String mail,String image)
      {
           
            try {
                PreparedStatement pt = c.prepareStatement("update user set image=? where email=?");
                pt.setString(1,image);
                pt.setString(2,mail);
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
            }
       
              
      }
      public boolean seconnecter(String mail,String mdp)
    {   boolean test= true;
        try {
           
            String stat = ("select user.nom,user.prenom,user.tel,user.email,user.mdp,user.creation,user.naissance from user where user.email='"+mail+"' and user.mdp='"+mdp+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            if (rs.next()) {
            test = true;
            }
            else 
            {
                System.out.println("verifier vos donnees!!!");
                test=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
      return test;  
        
    }
 
    
      public boolean verifiercompte(String mail, String mdp)
    { int active=-1;
      boolean test = false;
        try {
            String stat = ("select active from user where user.email='"+mail+"' and user.mdp='"+mdp+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery(); 
             if (rs.next()) {
                active =rs.getInt("active");
            }
            if(active==1)
            {
              test=true;
            }
            else if(active==0)
            {
                System.out.println("vous avez desactivez votre compte");
                test=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
       return test;         
    }
          public List<user> afficherAllchauffeur (String type)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+type+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String mail=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int nbr_course=rs.getInt("nbr_course");
                int experience=rs.getInt("experience");
                String user=rs.getString("type");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,mail,"xxxx",naiss,create,"image",user,nom_compte);
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
          
    public List<user> filtrer (String type,int act)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+type+"' and active='"+act+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String mail=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int nbr_course=rs.getInt("nbr_course");
                int experience=rs.getInt("experience");
                String user=rs.getString("type");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,mail,"xxxx",naiss,create,"image",user,nom_compte);
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
              public List<user> getTrier(String user)
              {
        
            List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+user+"' ORDER BY experience DESC");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();   
            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String email=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                String type=rs.getString("type");
                int nbr_course=rs.getInt("nbr_course");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int experience=rs.getInt("experience");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,email,"xxxx",naiss,create,"image",type,nom_compte);
                arr.add(client);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
     public List<user> getTrierByCourse(String user)
              {
        
            List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+user+"' ORDER BY nbr_course DESC");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();   
            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String email=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                String type=rs.getString("type");
                int nbr_course=rs.getInt("nbr_course");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int experience=rs.getInt("experience");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,email,"xxxx",naiss,create,"image",type,nom_compte);
                arr.add(client);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
     public List<user> getTrierByNom(String user)
              {
        
            List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+user+"' ORDER BY nom DESC");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();   
            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String email=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                String type=rs.getString("type");
                int nbr_course=rs.getInt("nbr_course");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int experience=rs.getInt("experience");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,email,"xxxx",naiss,create,"image",type,nom_compte);
                arr.add(client);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
      public List<user> afficherclient (String mail)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select user.nom,user.prenom,user.tel,user.email,user.creation,user.naissance,user.active,user.type,user.nbr_course from user  where email='"+mail+"' and active=1");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(1);
                String prenom=rs.getString(2);
                int tel=rs.getInt(3);
                String email=rs.getString(4);
                String create=rs.getString(5);
                String naiss=rs.getString(6);
                int active = rs.getInt(7);
                String type=rs.getString(8);
                int nbr_course=rs.getInt(9);
                user client =new user(nom, prenom, tel, mail, "xxxx", naiss, create, active, "image", type, nbr_course);
               // chauffeur ch = new chauffeur(cin,rib,1,per,nom_c,nom,prenom,tel,mail,"xxxx",naiss,create,active,"image");
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
         public List<user> afficherchauffeur(String mail)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select user.nom,user.prenom,user.tel,user.email,user.creation,user.naissance,user.active,user.type,user.nbr_course,user.cin,user.permis,user.nom_compte,user.rib_compte,user.experience from user  where email='"+mail+"' and active=1");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(1);
                String prenom=rs.getString(2);
                int tel=rs.getInt(3);
                String email=rs.getString(4);
                String create=rs.getString(5);
                String naiss=rs.getString(6);
                int active = rs.getInt(7);
                String type=rs.getString(8);
                int nbr_course=rs.getInt(9);
                int cin=rs.getInt(10);
                int permis=rs.getInt(11);
                String nom_compte=rs.getString(12);
                int rib_compte=rs.getInt(13);
                int experience=rs.getInt(14);
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,mail,"xxxx",naiss,create,"image","chauffeur",nom_compte);
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
            public List<user> finduser (String mail)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select user.id_u,user.nom,user.prenom,user.tel,user.email from user  where email='"+mail+"' and active=1");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                int id_u=rs.getInt(1);
                String nom=rs.getString(2);
                String prenom=rs.getString(3);
                int tel=rs.getInt(4);
                user client =new user(id_u,tel,nom,prenom,mail);
               // chauffeur ch = new chauffeur(cin,rib,1,per,nom_c,nom,prenom,tel,mail,"xxxx",naiss,create,active,"image");
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
    public List<user> finduserbyid (int id)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select user.nom,user.prenom,user.tel,user.email from user  where id_u='"+id+"' and active=1");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(1);
                String prenom=rs.getString(2);
                int tel=rs.getInt(3);
                String mail=rs.getString(4);
                user client =new user(id,tel,nom,prenom,mail);
               // chauffeur ch = new chauffeur(cin,rib,1,per,nom_c,nom,prenom,tel,mail,"xxxx",naiss,create,active,"image");
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
        public List<user> chercheruser(int cin)
    {   List<user> arr =new ArrayList<>();
        try {
            String stat = ("select user.nom,user.prenom,user.tel,user.email,user.creation,user.naissance,user.cin,user.permis,user.nom_compte,user.rib_compte,experience from user user.cin='"+cin+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) {
            
                String nom=rs.getString(1);
                String prenom=rs.getString(2);
                int tel=rs.getInt(3);
                String email=rs.getString(4);
                String create=rs.getString(5);
                String naiss=rs.getString(6);
                int active = rs.getInt(7);
                int ccin=rs.getInt(8);
                String per=rs.getString(9);
                String nom_c=rs.getString(10);
                int rib=rs.getInt(11);
            
                user us = new user(nom,prenom,tel,email,"xxxx",naiss,create,active,"image","client",0);
                //arr.add(ch); }
        } }catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
     return arr;
        
    }
  public void ajouterchauffeur(user client)
     {
        try {
            Statement st =c.createStatement();
            //String req="insert into  user,chauffeur values("+ch.getId_ch()+",'"+ch.getNom()+"','"+ch.getPrenom()+"')";
            st.executeUpdate("insert into  user (username,username_canonical,email,email_canonical,enabled,password)values('"+client.getNom()+"','"+client.getNom()+"','"+client.getMail()+"','"+client.getMail()+"','1','"+client.getMdp()+"')");
            st.executeUpdate("insert into  user (nom,prenom,tel,mail,mdp,naissance,creation,active,image,type,cin,permis,nom_compte,rib_compte,experience,nbr_course)values('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getTel()+"','"+client.getMail()+"','"+client.getMdp()+"','"+client.getNaissance()+"','"+client.getCreation()+"',1,'"+client.getImage()+"','"+client.getType()+"','"+client.getCin()+"','"+client.getPermis()+"','"+client.getNom_compte()+"','"+client.getRib_compte()+"','"+client.getExperience()+"','"+client.getNb_course()+"')");
           } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    public void ajouteruser(user client)
     {
      
        try {
            Statement st =c.createStatement();
            //String req="insert into  user,chauffeur values("+ch.getId_ch()+",'"+ch.getNom()+"','"+ch.getPrenom()+"')";
            st.executeUpdate("insert into  user (nom,prenom,tel,mail,mdp,naissance,creation,active,image,type,nbr_course)values('"+client.getNom()+"','"+client.getPrenom()+"','"+client.getTel()+"','"+client.getMail()+"','"+client.getMdp()+"','"+client.getNaissance()+"','"+client.getCreation()+"',1,'"+client.getImage()+"','"+client.getType()+"','0')");
            st.executeUpdate("insert into  user (username,username_canonical,email,email_canonical,enabled,password)values('"+client.getNom()+"','"+client.getNom()+"','"+client.getMail()+"','"+client.getMail()+"','1','"+client.getMdp()+"')");
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
          
    }
        public void modifierclient(String mail, String nom,String prenom,String naissance,int tel)
    { 
        try {
            
            PreparedStatement pt = c.prepareStatement("update user set nom=? , prenom=? ,tel=?,naissance=?  where email=?");
            
            pt.setString(1,nom);
            pt.setString(2,prenom);
            pt.setInt(3, tel);
            pt.setString(4,naissance);
            pt.setString(5, mail);
            pt.executeUpdate();
           
           
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
   public void modifierchauffeur(String email, String nom,String prenom,String naissance,int tel,int cin,int permis,String nom_compte,int rib_compte)
    { 
        try {
            
            PreparedStatement pt = c.prepareStatement("update user set nom=? , prenom=? ,tel=?,naissance=?,cin=?,permis=?,nom_compte=?,rib_compte=?  where email=?");
            
            pt.setString(1,nom);
            pt.setString(2,prenom);
            pt.setInt(3, tel);
            pt.setString(4,naissance);           
            pt.setInt(5, cin);
            pt.setInt(6, permis);
            pt.setString(7, nom_compte); 
            pt.setInt(8, rib_compte);
            pt.setString(9, email); 
            pt.executeUpdate();
           
           
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
          public List<user> afficherAll()
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int tel=rs.getInt("tel");
                String mail=rs.getString("email");
                String create=rs.getString("creation");
                String naiss=rs.getString("naissance");
                int active = rs.getInt("active");
                int cin=rs.getInt("cin");
                int permis=rs.getInt("permis");
                String nom_compte=rs.getString("nom_compte");
                int rib_compte=rs.getInt("rib_compte");
                int nbr_course=rs.getInt("nbr_course");
                int experience=rs.getInt("experience");
                String user=rs.getString("type");
                user client =new user(tel,active,cin,permis,rib_compte,experience,nbr_course,nom,prenom,mail,"xxxx",naiss,create,"image",user,nom_compte);
                arr.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(servicesuser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
}


