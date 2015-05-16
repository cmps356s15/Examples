package jpa.relations.entity;

public class OrderSummary {

    private String customerName;
    private Long totalQty;

    public OrderSummary(String customerName, Long totalQty) {
        this.customerName = customerName;
        this.totalQty = totalQty;
    }

    public String geCustomertName() {
        return customerName;
    }

    public void setCustomerName(String name) {
        this.customerName = name;
    }

    public Long getTotalQty() {
        return totalQty;
    }

    public void setTotalQty(Long totalQty) {
        this.totalQty = totalQty;
    }

}
