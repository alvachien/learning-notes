let rst = (0.1 + 0.2 == 0.3);
console.log(rst); // false
rst = (0.5 - 0.4 == 0.1);
console.log(rst); // false
rst = (0.5 + 0.25 == 0.75);
console.log(rst); // true

0.1.toString(2) // 0.1 => 二进制 
// 0.000110011001100110011   
0.5.toString(2) // 0.5 => 二进制 
// 0.1 
0.25.toString(2) // 0.25 => 二进制 
// 0.01 
0.125.toString(2) // 0.125 => 二进制 
// 0.001 

// 二进制保存的小数，转化为十进制，一定以`5`结尾！
