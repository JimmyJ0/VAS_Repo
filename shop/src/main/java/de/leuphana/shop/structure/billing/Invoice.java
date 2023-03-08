package de.leuphana.shop.structure.billing;

import java.util.HashMap;
import java.util.Map;

public class Invoice {
	private Map<Integer, InvoicePosition> invoicePositions;
	
	public Invoice() {
		invoicePositions = new HashMap<Integer, InvoicePosition>();
	}
	
	public void addInvoicePosition(InvoicePosition invoicePosition) {
		invoicePositions.put(invoicePosition.getPositionId(), invoicePosition);
	}

}