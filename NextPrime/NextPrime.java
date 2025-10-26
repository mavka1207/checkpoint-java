public class NextPrime {
    public static Integer nextPrime(Integer n) {
    if (n < 2) {
        return 2;
    }
        for (int i = n + 1; i < n * 2; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
            return 2;
        }

        public static boolean isPrime(Integer n) {
            if (n < 2) {
                return false;
            }
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    return false;
                }
        }
            return true;
    }
}