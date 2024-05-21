package litc.cardona.elias.servlets.entity.component;

public class Component {
    private String cname;
    private float quantity;
    private float price;

    public Component(String cname, float quantity, float price) {
        this.cname = cname;
        this.quantity = quantity;
        this.price = price;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public float getQuantity() {
        return quantity;
    }

    public void setQuantity(float quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}


