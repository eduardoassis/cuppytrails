//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-661 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2010.08.23 at 07:13:05 PM CEST 
//


package de.hybris.platform.cuppy.ws.openligadb.types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="leagueShortcut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="leagueSaison" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder =
{ "leagueShortcut", "leagueSaison" })
@XmlRootElement(name = "GetTeamsByLeagueSaison")
@SuppressWarnings("PMD")
public class GetTeamsByLeagueSaison
{

	protected String leagueShortcut;
	protected String leagueSaison;

	/**
	 * Gets the value of the leagueShortcut property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLeagueShortcut()
	{
		return leagueShortcut;
	}

	/**
	 * Sets the value of the leagueShortcut property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setLeagueShortcut(final String value)
	{
		this.leagueShortcut = value;
	}

	/**
	 * Gets the value of the leagueSaison property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getLeagueSaison()
	{
		return leagueSaison;
	}

	/**
	 * Sets the value of the leagueSaison property.
	 * 
	 * @param value
	 *           allowed object is {@link String }
	 * 
	 */
	public void setLeagueSaison(final String value)
	{
		this.leagueSaison = value;
	}

}
