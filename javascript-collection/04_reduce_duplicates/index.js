const arr = [
    { a: 1, b: 2},
    { b: 2, a: 1},
    { a: 1, b: 2, c: {a: 1, b: 2}},
    { b: 2, a: 1, c: {b: 2, a: 1}},
];

// 对象数组去重
//  只要对象的所有属性相同，则为相同对象；    

// Try 1. Not working
const newArr = [...new Set(arr)];
console.log(newArr);

// Try 2. Working but only for object with the corrected order of fields.
const newArr2 = [...arr];
for(let i = 0; i < newArr2.length; i ++) {
    for (let j = i + 1; j < newArr2.length; j ++) {
        if (JSON.stringify(newArr2[1]) === JSON.stringify(newArr2[j])) {
            newArr2.splice(j, 1);
            j --;
        }
    }
}


// Try 3. Working but only for object with the corrected order of fields.
const isObject = (val) => typeof val === "object" && val !== null;
function equals(val1, val2) {
    if (!isObject(val1) || !isObject(val2)) {
        return Object.is(val1, val2);
    }

    if (val1 === val2) return true;

    const val1keys = Object.keys(val1);
    const val2keys = Object.keys(val2);
    if (val1keys.length !== val2keys.length) {
        return false;
    }
    for (const key of val1keys) {
        if (!val2keys.includes(key)) {
            return false;
        }

        const res = equals(val1[key], val2[key]);
        if (!res) return false;
    }
    return true;
}

const newArr3 = [...arr];
for(let i = 0; i < newArr3.length; i ++) {
    for (let j = i + 1; j < newArr3.length; j ++) {
        if (equals(newArr3[i], newArr3[j])) {
            newArr3.splice(j, 1);
            j --;
        }
    }
}
