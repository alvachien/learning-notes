
/**
 * @param {number} num 
 */
function toChineseNumber(num) {
    const numStr = num.toString().replace(/(?=(\d{4})+$)/g, ',').split(',').filter(Boolean);
    
    const chars = ['零', '一', '二', '三', '四', '五', '六', '七', '八', '九'];
    const units = ['', '十', '百', '千'];
    const bigunits = ['', '万', '亿'];

    function handleZero(str) {
        // Case 1: 1004 
        // Case 2: 1200
        return str.replace(/零{2,}/g, '零')
            .replace(/零+$/g, '')
            ;
    }

    function _transform(n) {
        if (n === '0000') {
            return chars[0];
        }
        let result = '';
        for(let i = 0; i < n.length; i ++) {
            const c = chars[+n[i]];
            const u = units[n.length - 1 - i];
            if (c === chars[0]) {
                u = '';
            }
            result += c + u;            
        }
        result = handleZero(result);
        return result;
    }

    let finalrsult = '';
    for(let i = 0; i < numStr.length; i ++) {
        const part = numStr[i];
        const c = _transform(part);
        let bu = bigunits[numStr.length - 1 - i];
        if (c === chars[0]) {
            bu = '';
        }
        finalrsult += c + bu;
    }

    finalrsult = handleZero(finalrsult);

    return finalrsult;
}

console.log(toChineseNumber(45674576));

function handleUIInput() {
    var inputnum = document.getElementById('input-number');
}
