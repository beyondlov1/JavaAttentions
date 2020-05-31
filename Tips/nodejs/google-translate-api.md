### 官方的api有些问题 具体原因未知
要用这个 https://github.com/vitalets/google-translate-api
const translate = require('@vitalets/google-translate-api');

index.js: 要添加 tld:'cn'
const translate = require('@vitalets/google-translate-api');

translate('中文', {to: 'en',tld:'cn'}).then(res => {
    console.log(res.text);
    //=> I speak English
    console.log(res.from.language.iso);
    //=> nl
}).catch(err => {
    console.error(err);
});
