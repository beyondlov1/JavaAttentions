#### @JsonFormat

@JsonFormat添加在实体类的属性上,表示输出的格式

@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")

注意: 在输出时间时可以加在Date类型的属性上,  但是要加上 **timezone = "GMT+8"**, 否则会晚8小时

#### elasticsearch相关的注解

- @Document

  添加在实体类上

- @Id

  添加在id上, 添加之后会把这个属性和elasticsearch的id进行关联, 如果不希望两个相同就直接不加这个注解, 

  elastic会自己生成一个随机的id

- @Field

  添加在属性上, 规定属性的类型

#### @RestController

相当于controller和responseBody的结合

