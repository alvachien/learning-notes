const { intersection } = require('ramda');
var exec = require('./exercise');
var assert = require('chai').assert;

describe('Curry exercises', function() {
    it('exercise 1: words', () => {
        var rst = exec.words('Jingle bells Batman smells');
        assert.equal(4, rst.length);

        // New logic
        rst = exec.words2('Jingle bells Batman smells');
        assert.equal(4, rst.length);
        assert.deepEqual(rst, ['Jingle', 'bells', 'Batman', 'smells']);
    });

    it('exercise 1a: sentence', () => {
        var rst = exec.sentences(["Jingle bells Batman smells", "Robin laid an egg"]);
        assert.deepEqual(rst, [['Jingle', 'bells', 'Batman', 'smells'], ['Robin', 'laid', 'an', 'egg']]);
    });
});
