INSERT INTO CUSTOMER(ID, USER_NAME, PASSWORD) VALUES (1, "sangmin", "1234");
INSERT INTO CUSTOMER(ID, USER_NAME, PASSWORD) VALUES (2, "somin", "1234");
INSERT INTO CUSTOMER(ID, USER_NAME, PASSWORD) VALUES (3, "minsu", "1234");
INSERT INTO ACCOUNT(ID,ACCOUNT_NUMBER,CUSTOMER_ID,BANK_CODE,BALANCE,ACCOUNT_CODE) VALUES (1,"000-000000-00000",1,"HANA",1000,"FUND");
INSERT INTO ACCOUNT(ID,ACCOUNT_NUMBER,CUSTOMER_ID,BANK_CODE,BALANCE,ACCOUNT_CODE) VALUES (2,"111-000000-00000",2,"KB",2000,"FUND");
INSERT INTO ACCOUNT(ID,ACCOUNT_NUMBER,CUSTOMER_ID,BANK_CODE,BALANCE,ACCOUNT_CODE) VALUES (3,"222-000000-00000",3,"SC",3000,"SAVING");
INSERT INTO ACCOUNT(ID,ACCOUNT_NUMBER,CUSTOMER_ID,BANK_CODE,BALANCE,ACCOUNT_CODE) VALUES (4,"333-000000-00000",2,"HANA",4000,"SAVING");
INSERT INTO NUMBER_BOOK(ID,FROM_CUSTOMER_ID,TO_CUSTOMER_ID,PHONE_NUMBER,NUMBER_NAME) VALUES (1,1,2,"010-1111-1111","소민");
INSERT INTO NUMBER_BOOK(ID,FROM_CUSTOMER_ID,TO_CUSTOMER_ID,PHONE_NUMBER,NUMBER_NAME) VALUES (2,1,3,"010-2222-2222","민수");
INSERT INTO PHONE_NUMBER_ACCOUNT(ID,CUSTOMER_ID,ACCOUNT_ID) VALUES (1,2,4);
INSERT INTO PHONE_NUMBER_ACCOUNT(ID,CUSTOMER_ID,ACCOUNT_ID) VALUES (2,3,3);