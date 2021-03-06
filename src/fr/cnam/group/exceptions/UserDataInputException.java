/*
 * Nom de classe : UserDataInputException
 *
 * Description   : exception levée par une saisie incorrecte de données utilisateur.
 *
 * Auteurs       : Steven Besnard, Agnes Laurencon, Olivier Baylac, Benjamin Launay
 *
 * Version       : 1.0
 *
 * Date          : 09/01/2022
 *
 * Copyright     : CC-BY-SA
 */

package fr.cnam.group.exceptions;

public class UserDataInputException extends Exception{
    public UserDataInputException(String msg){
        super(msg);
    }
}
