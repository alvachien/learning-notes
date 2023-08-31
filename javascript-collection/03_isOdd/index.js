// Version 1.  
function isOdd(n) {
    return n % 2 === 1;
}

// Works only for positive numbers;
// isOdd(1) => true
// isOdd(5) => true
// isOdd(17) => true
// isOdd(10) => false
// isOdd(-1) => false 

// Version 2.  
function isOddv2(n) {
    return n % 2 !== 0;
}

// Works for positive numbers and negative numbers, but only for integers;
// isOddv2(1) => true
// isOddv2(17) => true
// isOddv2(10) => false
// isOddv2(-1) => false 
// isOddv2(-1.5) => true  


// Version 3.  
function isOddv3(n) {
    return n % 2 === 1 || n % 2 === -1;
}

// Works for positive numbers and negative numbers, but only for integers;
// isOddv2(1) => true
// isOddv2(17) => true
// isOddv2(10) => false
// isOddv2(-1) => true
// isOddv2(-3) => true  

