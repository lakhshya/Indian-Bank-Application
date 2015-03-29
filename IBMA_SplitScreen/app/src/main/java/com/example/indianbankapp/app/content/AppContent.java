package com.example.indianbankapp.app.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lakhshya on 05/06/14.
 */
public class AppContent {

    /**
     * An array of sample (content) items. Items are sorted by the ID of the item.
     */
    public static ArrayList<AppContentItem> ITEMS = new ArrayList<AppContentItem>();
    /**
     * A map of sample (content) items, by ID.
     */
    public static Map<Integer, AppContentProduct> PRODUCT_MAP = new HashMap<Integer, AppContentProduct>();
    private static AppContent ourInstance = new AppContent();


    static {
        //addProduct("About Us", "home.html");

        addSection("Loans");
        addProduct("IB Home Loan", "hl.html");
        addProduct("IB Vehicle Loan", "vl.html");
        addProduct("Education Loan", "el.html");

        addSection("Savings Bank Accounts");
        addProduct("SB Platinum", "sb_platinum.html");
        addProduct("SB Gold", "sb_gold.html");
        addProduct("SB Silver", "sb_silver.html");

        addSection("Term Deposits");
        addProduct("Ind Tax Saver", "tax_savings.html");
        addProduct("IB Savings Certificate", "savings_cert.html");
        addProduct("IB Tax Savings Certificate", "tax_cert.html");
        addProduct("Variable Recurring Deposits", "vrd.html");
        addProduct("Capital Gains", "capt_gains.html");

        addSection("NRI Accounts");
        addProduct("Non Resident Ordinary A/c", "non_resident_ordinary_account.html");
        addProduct("NRE SB A/c", "nre_sb_account.html");
        addProduct("NRE FD/RIP/RD A/c", "nre_accounts.html");
        addProduct("Resident Foreign Currency A/c", "resident_foreign_currency_account.html");
        addProduct("FCNR (B) A/c", "fcnr_accounts.html");

        addSection("Premium Services");
        addProduct("Ind Netbanking", "net.html");
        addProduct("Ind Mobile Banking", "mob.html");
        addProduct("Ind Phone Banking", "phone.html");
        addProduct("Money Gram", "money_gram.html");
        addProduct("Xpress Money", "xpress.html");
        addProduct("MCA Payment", "mca.html");


        addSection("Calculators");
        addCalculator("EMI Calculator", AppContentCalculator.TYPE_EMI);
        addCalculator("FD Calculator", AppContentCalculator.TYPE_FD);

        addSection("About Us");
        addProduct("CMD's Profile", "cmd.html");
        addProduct("ED's Profile", "ed.html");
        addProduct("Board of Directors", "board.html");
    }

    private static void addProduct(String title, String filename) {
        AppContentProduct acp = new AppContentProduct(title, filename);
        ITEMS.add(acp);
        PRODUCT_MAP.put(acp.id, acp);
    }

    private static void addCalculator(String title, int type) {
        ITEMS.add(new AppContentCalculator(title, type));
    }

    private static void addSection(String title) {
        ITEMS.add(new AppContentSection(title));
    }

    public static class AppContentItem {
        /**
         * Automates ID allotment to all the items. ID/Key begins from 0.
         */
        private static int nextId = 0;
        public int id;
        public String title;

        /**
         * @param title    The title of the product that will be displayed in the navigation drawer
         */
        protected AppContentItem(String title) {
            this.id = nextId++;
            this.title = title;
        }

        /**
         * @return The title/menu name for the AppContentItem
         */
        @Override
        public String toString() {
            return title;
        }
    }

    public static class AppContentProduct extends AppContentItem{
        public String url;

        /**
         * @param title    The title of the product that will be displayed in the navigation drawer
         * @param filename Just the name of the file and not the absolute path
         */
        public AppContentProduct(String title, String filename) {
            super(title);
            this.url = "file:///android_asset/" + filename;
        }

    }

    public static class AppContentCalculator extends AppContentItem{

        public static final int TYPE_EMI = 1;
        public static final int TYPE_FD = 2;

        public int type;
        /**
         * @param title    The title of the product that will be displayed in the navigation drawer
         */
        public AppContentCalculator(String title, int type) {
            super(title);
            this.type = type;
        }
    }

    public static class AppContentSection extends AppContentItem{
        /**
         * @param title    The title of the product that will be displayed in the navigation drawer
         */
        public AppContentSection(String title) {
            super(title);
        }
    }

}
