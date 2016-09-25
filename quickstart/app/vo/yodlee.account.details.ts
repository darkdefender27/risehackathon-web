import { Amount } from './amount.vo';

export class YodleeAccountDetails {
  container: string;
  accountName: string;
  accountNumber: string;
  accountStatus: string;
  availableCash: Amount;
  balance: Amount;

  availableCredit: Amount;
  totalCashLimit: Amount;
  totalCreditLine: Amount;
}
