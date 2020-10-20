var _ = require('ramda');

// Exercise 1
// ===============
// Partial apply to remove all parameters

var words = function(str) {
  return _.split(' ', str);
}

var words2 = _.split(' ');

// Exercise 1a
// ===============
// Using map to create a new 'words' to make it compatible with string array

var sentences = _.map(words2);


module.exports = { 
  words: words,
  words2: words2,
  sentences: sentences,
  // filterQs: filterQs,
  // max: max,
  // slice: slice,
  // take: take
};
