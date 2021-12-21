package fr.cnam.group;

import java.io.File;
import java.io.IOException;

import static fr.cnam.group.DataHandler.*;

public class Administrateur extends Account{
    public Administrateur(String identifiant, char[] password) throws Exception {
        super(identifiant, password);
    }

    @Override
    public boolean checkPassword(char[] checkedPassword) {
        return super.checkPassword(checkedPassword);
    }

    @Override
    public boolean modify(Account newAccount) throws Exception {



        Administrateur[] accounts = new Administrateur[listeAdmins.size()+ annuaire.size()];
        System.out.printf("there's %d accounts in HashMap\n", listeAdmins.size());
        if (getIdentifiant().equals(newAccount.getIdentifiant())){
            listeAdmins.replace(getIdentifiant(), (Administrateur) newAccount);
        }else {
            if (listeAdmins.putIfAbsent(newAccount.getIdentifiant(), (Administrateur) newAccount) == null) {
                listeAdmins.remove(getIdentifiant());

            } else {
                throw new Exception("erreur lors de l'ajout au système");
            }
        }
        System.out.println("Account replaced in HashMap");


        accounts = listeAdmins.values().toArray(accounts);



        int refClient = 0;
        clearFile(new File(ACCOUNT_FILE_PATH));
        for(Administrateur a : accounts) {
            System.out.println("reading from extracted data : " + a.getIdentifiant());
            if (a != null){
                System.out.println("checking " + a.getIdentifiant());
                DataHandler.addToFile(a);

            } else {

                if(refClient ==  0){
                    System.err.println("admin read is null");
                    throw new Exception("no match for Account");
                }
                else{
                    System.out.println("end of accounts list");

                }

            }
            refClient++;
        }

        return true;
    }

    @Override
    public boolean remove() throws Exception {
        Administrateur[] accounts = new Administrateur[listeAdmins.size()+DataHandler.annuaire.size()];
        System.out.printf("there's %d accounts in HashMap\n", DataHandler.annuaire.size());

        listeAdmins.remove(getIdentifiant());
        System.out.println("administrateur removed from HashMap");

        accounts = listeAdmins.values().toArray(accounts);


        int refClient = 0;
        clearFile(new File(ACCOUNT_FILE_PATH));
        for(Administrateur a : accounts) {
            System.out.println("reading from extracted data : " + a.getIdentifiant());
            if (a != null){
                System.out.println("checking " + a.getIdentifiant());
                DataHandler.addToFile(a);

            } else {

                if(refClient ==  0){
                    System.err.println("admin read is null");
                    throw new Exception("no match for Administrateur");
                }
                else{
                    System.out.println("end of accounts list");

                }

            }
            refClient++;
        }

        return true;
    }

    @Override
    public boolean ajouter() throws Exception {
        try {
            if (listeAdmins.putIfAbsent(getIdentifiant(), this) == null){
                System.out.println("admin créé");
                addToFile(this);
                return true;
            }
            else{
                System.out.println("error while putting to HashMap");
                throw new Exception("erreur lors de l'ajout au système");

            }

        }catch (IOException e) {
            System.err.println(e.getMessage());
            System.out.println("removing from HashMap");
            listeAdmins.remove(getIdentifiant());
            throw new Exception("erreur lors de l'inscription dans le fichier");

        }
    }

    @Override
    public String getIdentifiant() {
        return super.getIdentifiant();
    }

    @Override
    public char[] getPassword() {
        return super.getPassword();
    }
}
