import { Amount } from './amount.vo';

export class YodleeAccountDetails {
  container: string;
  accountName: string;
  accountNumber: string;
  accountStatus: string;
  availableCash: Amount;
  availableCredit: Amount;
  balance: Amount;
  totalCashLimit: Amount;
  totalCreditLine: Amount;
}
