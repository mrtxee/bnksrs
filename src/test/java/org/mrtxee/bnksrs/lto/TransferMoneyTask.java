package org.mrtxee.bnksrs.lto;

import lombok.Data;

@Data
//@AllArgsConstructor
public class TransferMoneyTask {
    private Long payee;
    private Long recipient;
    private Double amount;

    public TransferMoneyTask(Long payee, Long recipient, Double amount) {
        this.payee = payee;
        this.recipient = recipient;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferMoneyTask{" +
                "payee=" + payee +
                ", recipient=" + recipient +
                ", amount=" + amount +
                '}';
    }
    //    private static void generateTasks() {
//        final long[] agents = {4003000000000001L, 4003000000000002L, 4003000000000003L};
//        final int TRANSACTION_LIMIT = 4;
//        final int TASKS_LIMIT = 499;
//        Random r = new Random(agents[0]);
//        System.out.printf("payee\trecipient\tamount\n");
//
//        for(int i=0; i<TASKS_LIMIT; i++){
//            long payee = agents[r.nextInt(agents.length)], recipient;
//            do{
//                recipient = agents[r.nextInt(agents.length)];
//            }
//            while(payee==recipient);
//            int amount = r.nextInt(TRANSACTION_LIMIT)+1;
//            System.out.printf("%s\t%s\t%s\n", payee, recipient, amount);
//        }
//
//    }

//    public static void main(String[] args) {
////      generateTasks();
////      4003000000000001	4003000000000003	1
////        TransferMoneyTask tmt = new TransferMoneyTask(4003000000000001L, 4003000000000003L, 1);
////        System.out.println(tmt);
//
//    }
}
