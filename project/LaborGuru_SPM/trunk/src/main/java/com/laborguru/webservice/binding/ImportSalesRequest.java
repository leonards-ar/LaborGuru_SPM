
package com.laborguru.webservice.binding;

import java.util.List;

import com.laborguru.model.HistoricSales;

/** 
 * Schema fragment(s) for this class:
 * <pre>
 * &lt;xs:element xmlns:ns="http://www.laborguru.com/spm/webservices/sales" xmlns:xs="http://www.w3.org/2001/XMLSchema" name="importSalesRequest">
 *   &lt;xs:complexType>
 *     &lt;xs:sequence>
 *       &lt;xs:element type="ns:SalesItem" name="salesItem" minOccurs="1" maxOccurs="unbounded"/>
 *     &lt;/xs:sequence>
 *   &lt;/xs:complexType>
 * &lt;/xs:element>
 * </pre>
 */
public class ImportSalesRequest {
    private List<HistoricSales> historicSales;

    /** 
     * Get the list of @link{com.laborguru.model.HistoricSales} element items.
     * 
     * @return list
     */
    public List<HistoricSales> getHistoricSales() {
        return historicSales;
    }

    /** 
     * Set the list of @link{com.laborguru.model.HistoricSales} element items.
     * 
     * @param list
     */
    public void setHistoricSales(List<HistoricSales> list) {
        historicSales = list;
    }
}
