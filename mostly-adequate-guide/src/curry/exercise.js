var _ = require('ramda');

// 练习 1
//==============
// 通过局部调用（partial apply）移除所有参数
// Partial apply to remove all parameters

var words = function (str) {
  return _.split(' ', str);
}

var words2 = _.split(' ');

// 练习 1a
//==============
// 使用 `map` 创建一个新的 `words` 函数，使之能够操作字符串数组
// Using map to create a new 'words' to make it compatible with string array

var sentences = _.map(words2);

// 练习 2
//==============
// 通过局部调用（partial apply）移除所有参数
// Remove parameters

var filterQs = function (xs) {
  return _.filter(function (x) {
    return _.match(/q/i, x);
  }, xs);
};

var filterQs2 = _.filter(_.match(/q/i));

// 练习 3
//==============
// 使用帮助函数 `_keepHighest` 重构 `max` 使之成为 curry 函数

// 无须改动:
var _keepHighest = function (x, y) { return x >= y ? x : y; };

// 重构这段代码:
var max = function (xs) {
  return _.reduce(function (acc, x) {
    return _keepHighest(acc, x);
  }, -Infinity, xs);
};

var max2 = _.reduce(_keepHighest, -Infinity);

// 彩蛋 1:
// ============
// 包裹数组的 `slice` 函数使之成为 curry 函数
// //[1,2,3].slice(0, 2)
// var slice = undefined;
var slice2 =  _.curry(function(start, end, xs) { return xs.slice(start, end); });

// 彩蛋 2:
// ============
// 借助 `slice` 定义一个 `take` curry 函数，该函数调用后可以取出字符串的前 n 个字符。
var take = slice2(0);


module.exports = {
  words: words,
  words2: words2,
  sentences: sentences,
  filterQs: filterQs,
  filterQs2: filterQs2,
  max: max,
  max2: max2,
  slice2: slice2,
  take: take
};
