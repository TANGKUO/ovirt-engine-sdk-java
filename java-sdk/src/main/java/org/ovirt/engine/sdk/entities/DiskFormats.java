
package org.ovirt.engine.sdk.entities;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DiskFormats complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiskFormats">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="disk_format" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiskFormats", propOrder = {
    "diskFormats"
})
public class DiskFormats {

    @XmlElement(name = "disk_format")
    protected List<String> diskFormats;

    /**
     * Gets the value of the diskFormats property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the diskFormats property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiskFormats().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDiskFormats() {
        if (diskFormats == null) {
            diskFormats = new ArrayList<String>();
        }
        return this.diskFormats;
    }

    public boolean isSetDiskFormats() {
        return ((this.diskFormats!= null)&&(!this.diskFormats.isEmpty()));
    }

    public void unsetDiskFormats() {
        this.diskFormats = null;
    }

}