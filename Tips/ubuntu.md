### open terminal
Ctrl+Alt+T

### ubuntu 20.04 中文输入法 （ibus-rime）
sudo apt-get install ibus-rime
ibus restart


设置rime默认简体

这时rime默认的是繁体，很令人头疼。
nano ~/.config/ibus/rime/build/luna_pinyin.schema.yaml
打开后翻到最后面

switches:
  - name: ascii_mode
    reset: 0
    states: ["中文", "西文"]
  - name: full_shape
    states: ["半角", "全角"]
  - name: simplification
    states: ["漢字", "汉字"]
  - name: ascii_punct
    states: ["。，", "．，"]

在- name: simplification下另起一行输入reset: 1保存退出。
下面是完成后的样子

switches:
  - name: ascii_mode
    reset: 0
    states: ["中文", "西文"]
  - name: full_shape
    states: ["半角", "全角"]
  - name: simplification
    reset: 1
    states: ["漢字", "汉字"]
  - name: ascii_punct
    states: ["。，", "．，"]


下面这种设置没有试过可以尝试。。。。
~/.config/ibus/rime/default.yaml

schema_list:   
  - schema: luna_pinyin_simp #simp是简体，第一位是默认输入法 
menu:
  page_size: 9 #每页候选词个数
ascii_composer:
  switch_key:
    Shift_L: commit_code #左shift提交字母