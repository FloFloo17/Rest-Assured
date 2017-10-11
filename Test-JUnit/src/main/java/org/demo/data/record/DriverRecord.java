/*
 * Created on 2017-09-29 ( Date ISO 2017-09-29 - Time 15:54:56 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.0.0
 */


package org.demo.data.record;

import java.io.Serializable;
import javax.validation.constraints.*;


/**
 * Java bean for entity "Driver" <br>
 * Contains only "wrapper types" (no primitive types) <br>
 * Can be used both as a "web form" and "persistence record" <br>
 * 
 * @author Telosys Tools Generator
 *
 */
public class DriverRecord implements Serializable
{
    private static final long serialVersionUID = 1L;

    @NotNull
    private Integer id ; // int // Id or Primary Key

    @NotNull
    private String nom ;  // String 

    /**
     * Default constructor
     */
    public DriverRecord() {
        super();
    }
    
    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR ID OR PRIMARY KEY 
    //----------------------------------------------------------------------
    /**
     * Set the "id" field value
     * This field is mapped on the database column "id" ( type "", NotNull : true ) 
     * @param id
     */
	public void setId( Integer id ) {
        this.id = id ;
    }
    /**
     * Get the "id" field value
     * This field is mapped on the database column "id" ( type "", NotNull : true ) 
     * @return the field value
     */
	public Integer getId() {
        return this.id;
    }

    //----------------------------------------------------------------------
    // GETTER(S) & SETTER(S) FOR OTHER DATA FIELDS 
    //----------------------------------------------------------------------

    /**
     * Set the "nom" field value
     * This field is mapped on the database column "nom" ( type "", NotNull : true ) 
     * @param nom
     */
    public void setNom( String nom ) {
        this.nom = nom;
    }
    /**
     * Get the "nom" field value
     * This field is mapped on the database column "nom" ( type "", NotNull : true ) 
     * @return the field value
     */
    public String getNom() {
        return this.nom;
    }

    //----------------------------------------------------------------------
    // toString METHOD
    //----------------------------------------------------------------------
    @Override
    public String toString() { 
        StringBuffer sb = new StringBuffer(); 
        sb.append(id);
        sb.append("|");
        sb.append(nom);
        return sb.toString(); 
    } 



}
