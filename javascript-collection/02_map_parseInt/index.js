
['1', '2', '3'].map(parseInt);

// Output is
// [1, NaN, NaN]

// Why?
// ['1', '2', '3'].map(parseInt);
//  == >
// [parseInt('1', 0), parseInt('2', 1), parseInt('3', 2)]
//
