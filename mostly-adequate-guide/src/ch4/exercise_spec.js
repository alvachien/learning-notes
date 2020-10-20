var exec = require('./exercise');
var assert = require('chai').assert;

describe('Curry exercises', function() {
    it('exercise 1: words', () => {
        var rst = exec.words('My First smart Test');
        assert.equal(4, rst.length);

        // New logic
        rst = exec.words2('My First smart Test');
        assert.equal(4, rst.length);
        assert.deepEqual(rst, ['My', 'First', 'smart', 'Test']);
    });

    it('exercise 1a: sentence', () => {
        var rst = exec.sentences(["my smart test", "My second Smart test"]);
        assert.deepEqual(rst, [['my', 'smart', 'test'], ['My', 'second', 'Smart', 'test']]);
    });

    it('exercise 2', function(){
        var rst = exec.filterQs2(['my', 'second', 'exercise']);
        assert.deepEqual(rst, ['my', 'second', 'exercise']);
    });    
});
