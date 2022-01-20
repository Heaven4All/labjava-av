<input type="text" [(ngModel)]="amount" placeholder="Type Amount of Dollars Here">
Amount: {{ amount | currency}}

<!-- If the value of "amount" is not numeric, the application will stop responding -->