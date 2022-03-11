var _ = require('ramda');

var compose = function(f, g) {
  return function(x) {
    return f(g(x));
  }
};

// Example 1.
var toUpperCase = function(x) { return x.toUpperCase(); };
var exclaim = function(x) { return x + '!'; };
var shout = compose(exclaim, toUpperCase);

// Example 2.
var head = function(x) { return x[0]; };
var reverse = _.reduce(function(acc, x) {
  return [x].concat(acc);
}, []);
var last = compose(head, reverse);

// Associativity 结合律
var associative = compose(f, compose(g, h)) == compose(compose(f, g), h);

module.exports = {
  compose: compose,
  shout: shout,
  last: last,
};
