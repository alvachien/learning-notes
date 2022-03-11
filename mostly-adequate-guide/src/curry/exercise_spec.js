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

    it('Test deepEqual', function(){
        var rst = exec.filterQs2(['my', 'second', 'exercise']);
        assert.deepEqual(rst, ['my', 'second', 'exercise']);
    });
    
    it('Test max', function(){
        assert.equal(exec.max([323,523,554,123,5234]), 5234);
    });

    it('Test max2', function(){
        assert.equal(exec.max2([323,523,554,123,5234]), 5234);
    });

    it('Test slice2', function(){
        assert.deepEqual(exec.slice2(0, 2, [323,523,554,123]), [323, 523]);
    });
    it('Test slice2, 2', function(){
        assert.deepEqual(exec.slice2(0)(2)([323,523,554,123]), [323, 523]);
    });

    it('Test take', function(){
        assert.deepEqual(exec.take(2)([323,523,554,123]), [323, 523]);
    });
});
