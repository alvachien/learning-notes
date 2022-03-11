var exec = require('./exercise');
var assert = require('chai').assert;

describe('Compose exercises', function() {
    it('sample 1: shout', () => {
        var rst = exec.shout('show me the money');
        assert.equal("SHOW ME THE MONEY!", rst);
    });

    it('sample 2: last', () => {
        var rst = exec.last(['jumpkick', 'roundhouse', 'uppercut']);
        assert.equal("uppercut", rst);
    });
});
