import java.util.ArrayList;
import java.util.List;

class Stock {
    private String symbol;
    private float price;
    private List<Investor> investors;

    Stock(String symbol, float price) {
        this.symbol = symbol;
        this.price = price;
        this.investors = new ArrayList<>();
    }

    void registerInvestor(Investor investor) {
        investors.add(investor);
    }

    void unregisterInvestor(Investor investor) {
        investors.remove(investor);
    }

    void updatePrice(float price) {
        this.price = price;
        notifyInvestors();
    }

    private void notifyInvestors() {
        for (Investor investor : investors) {
            investor.update(this, price);
        }
    }
}

class Investor {
    private String name;
    private List<Stock> stocks;

    Investor(String name) {
        this.name = name;
        this.stocks = new ArrayList<>();
    }

    void investInStock(Stock stock) {
        stocks.add(stock);
        stock.registerInvestor(this);
    }

    void divestFromStock(Stock stock) {
        stocks.remove(stock);
        stock.unregisterInvestor(this);
    }

    void update(Stock stock, float price) {
        System.out.println(name + "  update for stock " + stock + ": $" + price);
    }
}

public class Ass8a {
    public static void main(String[] args) {
        Stock apple = new Stock("apple", 150.0f);
        Stock google = new Stock("google", 2500.0f);

        Investor investor1 = new Investor("Seka");
        Investor investor2 = new Investor("Beka");

        investor1.investInStock(apple);
        investor2.investInStock(google);

        apple.updatePrice(155.0f);
        google.updatePrice(2600.0f);

        investor1.divestFromStock(apple);

        apple.updatePrice(160.0f);
    }
}
