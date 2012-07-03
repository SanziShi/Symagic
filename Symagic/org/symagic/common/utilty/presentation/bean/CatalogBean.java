/**
 * 
 */
package org.symagic.common.utilty.presentation.bean;

import java.util.List;

/** 
 * <!-- begin-UML-doc -->
 * <!-- end-UML-doc -->
 * @author hao
 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
 */
public class CatalogBean {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String name;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private Integer ID;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private String desc;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML 至 Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private List<CatalogBean> childCatalog;
	
	public List<CatalogBean> getChildCatalog() {
		return childCatalog;
	}
	public void setChildCatalog(List<CatalogBean> childCatalog) {
		this.childCatalog = childCatalog;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}

}