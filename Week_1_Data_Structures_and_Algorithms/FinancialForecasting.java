package Week_1_Data_Structures_and_Algorithms;

    public class FinancialForecasting {
        public static void main(String[] args) {
            double initialValue = 3000;
            double growthRate = 0.008;
            int years = 4;

            double futureValue = Forecast.recursiveForecast(initialValue, growthRate, years);
            System.out.println(" value in future " + futureValue);
        }
    }

    class Forecast {
        public static double recursiveForecast(double amount, double rate, int years) {
            if (years == 0) return amount;
            return recursiveForecast(amount, rate, years - 1) * (1 + rate);
        }
}
