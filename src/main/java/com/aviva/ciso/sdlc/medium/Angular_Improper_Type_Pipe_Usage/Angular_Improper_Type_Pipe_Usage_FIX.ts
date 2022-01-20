<input type="text" [(ngModel)]="amount" placeholder="Type Amount of Dollars Here">

<!-- FIX -->
Amount: {{ parseInt(amount) | currency}}

<!-- Here we use parseInt to be sure that an int is passed to the pipe. -->