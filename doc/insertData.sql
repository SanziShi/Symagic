use bookshop;

-- 向admin表中插入数据
insert into admin (adminname, password) values ("管理员", md5(12));
insert into admin (adminname, password) values ("单小熙", md5(12));

-- 向book表插入数据
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787538296846.png","曾国藩和他的湘军","张云,韩洪泉",
                          "辽宁教育出版社","2012-04-01",1,340	,"平装"	,"16开",32.8,0.69,130,
                          "梁启超对世人说：“曾文正者，岂唯近代，盖有史以来不一二睹之大人也已；
                          岂唯我国，抑全世界不一二睹之大人也已。”
　　                      毛泽东对友人黎锦熙说：“愚于近人，独服曾文正，观其收拾洪杨一役，
                          完满无缺。使以今人易其位，其能如彼之完满乎？”
　　                      曾国藩，中国近代史上影响最大的人物。
　　                      以一介文臣统领私人武装湘军湘勇，凭统驭之道驾驭千军万马。
　　                      历史上的真实的曾国藩，是如何能以私家之兵力挽狂澜，
                          又如何在平定太平天国之后自削兵权？
　　                       一代文臣武将曾国藩，大清三百年中兴名臣，出将入相，内圣外王",
                          "9787538296846","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787539949789.png","银河帝国2：基地与帝国","阿西莫夫 著，叶李华　译",
                          "江苏文艺出版社","2012-06-01",1,230	,"平装"	,"16开",29.9,0.69,50,
                          "人类蜗居在银河系的一个小角落——太阳系，在围绕太阳旋转的第三颗行星上，生活了十多万年之久。
　　人类在这个小小的行星（他们称之为“地球”）上，建立了两百多个不同的行政区域（他们称之为“国家”），直到地球上诞生了第一个会思考的机器人。
　　在机器人的帮助下，人类迅速掌握了改造外星球的技术，开启了恢弘的星际殖民运动；人类在银河系如蝗虫般繁衍扩张，带着他们永不磨灭的愚昧与智慧、
贪婪与良知，登上了一个个荒凉的星球，并将银河系卷入漫长的星际战国时代，直至整个银河被统一，
一个统治超过2500万个住人行星、疆域横跨十万光年、总计数兆亿人口的庞大帝国崛起——银河帝国。
　　一个微妙的转折发生在银河帝国建国后的12020年。哈里·谢顿，这个刚满32岁的年轻数学家，开创了“心理史学”，这门学科能用数学公式准确推演全人类的未来
——“预言”从此成为一门可以信任的科学，人类由此可以看见未来。
谢顿的第一个预言是：虽然毫无征兆，但已存在一万两千年之久的银河帝国即将灭亡。",
                          "9787539949789","在架");
 insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787222073715.png","西风多少恨.吹不散眉弯","白落梅 著",
                          "云南人民出版社","2011-04-01",1,272	,"平装"	,"32开",28,0.56,125,
                          "纳兰容若的前世，是一朵在佛前修炼过的金莲，贪恋了人间烟火的颜色和气味，注定今生这场红尘游历。
                          所以他有冰洁的情怀，有如水的禅心，有悲悯的爱恋。纳兰容若的一生，
                          沿着宿命的轨迹行走，不偏不倚，不长不短，整整三十一载。在佛前，
                          他素淡如莲，却可以度化苍生；在人间，他繁华似锦，却终究不如一株草木。",
                          "9787222073715","下架");     
   insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787539936680.png","那些回不去的年少时光：终场","桐华 著",
                          "江苏文艺出版社","2010-04-01",1,284	,"平装"	,"16开",23.8,0.63,157,
                          "故事虽落幕，青春不终场！经过孤独的小学时光，混乱的初中生涯，罗琦琦来到了高中。和所有走过那段岁月的人一样，面对高考的折磨，罗琦琦虽然不情愿，却也无法做到不在乎，学习并不好的她，性格倔强不服输的她，究竟如何才能完成完美转身？
　　爱情，也是挑战她的另一大命题。她和他又走到了一起，他对她似乎已经倾心，只是她们身后还有一个他。她该如何面对，又该如何选择？
　　青春期的友情叫人唏嘘和心碎，青春期爱情叫人甜蜜又纠结，世纪末的社会如此复杂，叫人难以理清头绪。罗琦琦和她的同学们，就这样孤独而热闹地，混沌而逐渐清醒地成长起来了……
　　这是最完美的结局，也有最辛酸的告别，还有最残忍的长大，以及最难忘的回忆！",
                          "9787539936680","在架");   
   insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787532272334.png","素描的诀窍（经典版）","［美］伯特•多德森 著 蔡强 译",
                          "上海人民美术出版社","2011-04-01",1,222	,"平装"	,"16开",28,0.65,345,
                          "《素描的诀窍（经典版）》中的每一章都包含一些设计，这些设计是让读者对所讨论的诀窍进行运用。每章的最后都有一个回顾及设计自我评估部分。这些设计都有的放矢，要求在一定时间内解决一定数量的问题。其中有一些设计要求明确，从特殊角度对物体进行观察，或是夸大现实。这样做并不是为了要画出好看的图，而是要让你了解新的观察方法并作出反应。
　　这本书中包含了大量作画的类型——快速作画、草图作画、写生簿作画以及完整的构图作画。有些地方使用了照片作为辅助，以免产生不真实的印象。除非有另行注明，书中绘画都为作者本人所作。
　　读者可以按顺序阅读书中各章内容设计。不过，如果你也像我一样相信机运，倒也是妨根据情况选择最为适合自己的阅读顺序。
　　55种提高绘画能力的具体诀窍，48节帮助掌握技巧的自助课程，8张评估自身进步的自评列表，让它们带你去重新认识视觉艺术。",
                          "9787532272334","在架");  
   insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787535838858.png","游园惊梦","夏达",
                          "湖南少儿出版社","2008-07-01",1,85,"平装"	,"大16开",24,0.50,456,
                          "细细翻过夏达的《游园惊梦》，再次有一个奢侈的机会，尝试着在这个并不算太寒冷的冬天掩卷，喝一杯热茶，想一些事情，想到一首《燕燕于飞》的诗。
　　夏达是个画画很随性的姑娘，曾经为我们的一本杂志绘制一张封面，画一个少女躺在盛夏的浅水莲叶中，极美丽，美术总监思谋良久，说实在太柔，不合杂志的意趣。夏达便也不生气，说没什么，那是她喜欢画的，用不用，并没什么关系。
　　看了浅水中的仰卧的少女和莲花，只不过被分割在不同的画面中，还有桃花、静止的蝶月和沉睡的夏荫中的少年。
　　五个小故事都说神怪，可是并不多么神，更不奇怪，甚至我不能判断这些能否被补称“故事”，因为真是太简单了，只有很久的凝望、茫然的等待和一刹的相拥，到此这个故事就终结报。如果是在电影中，也许只要二十秒就可以结束的一个故事，
但是夏达需要画很久，问她为什么要画这些，她大概也会照旧回答说这是她想画的。",
                          "9787535838858","在架"); 
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787101052978.png","三国志","（晋）陈寿，裴松之 著",
                          "中华书局","2006-09-01",1,867,"精装"	,"32开",36,0.72,500,
                          "三国是我国封建社会历史发展过程中具有特色的历史时期，即魏、蜀、吴三国鼎立。三国的历史大致经过了九十年，也就是说从公元190年军阀混战始至公元280年晋灭吴统一南方止。但也有不少史学家以曹丕称帝的公元220年算起。如果仔细考察三国的历史，还是以公元190年算起更为科学，因为在魏、蜀、吴三国形成之前，确有一段形成过程。
　　三国是东汉王朝的继续。东汉末年，豪强地主土地所有制迅速发展，土地兼并异常激烈，宦官、外戚两个集团的交相干政和互相倾轧，更加造成了政治的极端黑暗和朝廷腐败，再加上对羌族的连年用兵和自然灾害的不断袭击，阶级矛盾日趋尖锐，终于激起了公元184年的黄巾军起义。起义军在张角、张宝、张梁等人领导下，迅速发展起来，其规模声势严重威胁着东汉王朝的统治。汉灵帝为之恐慌，先后派兵围剿黄巾军，首先以何皇后的哥哥何进为大将军，率军屯驻洛阳近郊，派卢植赴河北讨伐张角，派皇甫嵩、朱携赴颍川（今河南禹县）讨伐以波才为领袖的另一支黄巾军。
　　在颍川战场上，波才领导的黄巾军英勇奋战，拼杀顽强，朱携和皇甫嵩屡遭失败。后来皇甫嵩利用火攻，借大风天气，搅乱了农民军的队伍，致使农民军几万人牺牲，波才最后战死。",
                          "9787101052978","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787532544400.png","孙子兵法","（春秋）孙武　撰，（三国）曹操　注，郭化若 今译",
                          "上海古籍出版社","2006-07-01",1,175,"平装","16开",20,0.65,1024,
                          "《孙子兵法》是我国最古老、最杰出的一部兵书，历来备受推崇，研习者辈出。我们现在所能见到的最早为《孙子兵法》作注的乃一代枭雄曹操，其注字里行间不乏真知灼见。当代著名军事家郭化若更是长期研究《孙子兵法》，撰有《孙子译注》一书，不但为《孙子兵法》全文作了校勘、注释、今译，还撰写了题解及考订、评论文字，既有较高的学术水平，
                          又浅显易懂。本书即在郭化若《孙子译注》的基础上用红字加上了从宋本《十一家注孙子》中辑出的曹操的注，将孙武、曹操、郭化若的军事思想融为一书，以裨读者把握我国两千年来兵学之精华。书后附有《史记。孙子列传》，同时还附上郭化若《孙子译注》本的《前言》、《再版的话》。",
                          "9787532544400","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787214078476.png","做最好的共产党员","汪维宏　等著",
                          "江苏人民出版社","2011-10-01",1,179,"平装","16开",22,0.62,235,
                          "最好的党员是凡人
　　最好的党员是好人
　　最好的党员是能人
　　最好的党员是领头人
　　他们就在你身边
　　你也可以成为最好的党员
　　中央倡导的“创先争优”活动正在全国各地如火如荼地展开，作为一名共产党员，应该是本单位和本基层党组织这一活动的首要发起者和实践者。
如何将党的方针政策落到实处，如何将我们的日常工作做到最好，如何做到让群众满意人民认可？这是我们每一个党员应该思考的根本问题，也是攸关“创先争优”活动成败的根本问题。作为“创先争优系列读本”中的一本，《做最好的共产党员》一书回答了“在现时期，什么是最好的共产党员，
怎样做一个最好的共产党员”这一基本问题，应该成为我们基层党员学习实践的指南。",
                          "9787214078476","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787505721456.png","东方哲学简史（珍藏插图版）","（锡兰）L•A•贝克 著 赵增越 译",
                          "中国友谊出版公司","2006-01-01",1,289,"平装","16开",35,0.36,50,
                          "《东方哲学简史》(珍藏插图版)作者以一个理解东方精神的西方人的眼光，阐释着超凡脱俗的印度古典哲学、瑜珈与佛教和神秘而充满生命激情的波斯苏菲教，以及讲求个人道德完美主义的孔孟之道 、追求精神绝对自由的老子哲学。在欧洲，哲学和信仰毋宁说是某种装饰、而不像东方那样是生活本身。东方生命智慧将是拯救现代西方文明走出困境的精神食粮。这些思想把哲学之魂推展为包罗万象的博大体系，
                          它们还将推展到更广泛、更为精深的领域和范围。西方仍然在期待着来自东方的征服。",
                          "9787505721456","下架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio, marketprice,discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787549500444.png","古犹太教","（德）马克思•韦伯（Max Weber） 著 康乐，简惠美 译",
                          "广西师范大学出版社","2010-09-01",1,569,"精装","32开",50,0.66,74,
                          " 本文原为《古犹太教》第一篇第一章的注。在《宗教社会学论文集》一书里，韦伯习惯在各书的首章里说明他在处理此一问题时，
                          所采用的方法、重要参考文献以及更重要的研究史的回顾。",
                          "9787549500444","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787101074888.png","老子道德经注","楼宇烈 校 （魏）王弼 注",
                          "中华书局","2011-01-01",1,216,"精装","32开",19,0.70,1567,
                          " 王弼《老子道德经注》是以比较古老的《老子》文本为底本，注文同时保存了某些老子古意，并代表三国魏晋玄学思想，因此是《老子》一书最重要的注释之一，也是研究王弼思想的重要著作。楼宇烈先生以浙江书局刻明华亭张之象本为底本，参校数十种重要版本及相关徵引著述，
                          是目前唯一，也是最精审的有关王弼《老子道德经注》校勘注释本。",
                          "9787101074888","在架");

insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787510407550.png","乌合之众——大众心理研究","古斯塔夫•勒庞（Gustave Le Boin） 著 戴光年 译",
                          "新世界出版社","2010-01-01",1,182,"平装","16开",24.8,0.33,124,
                          " 《乌合之众——大众心理研究》是解析群体心理的经典名著，虽然是一部学术性著作，但语言生动流畅，分析鞭辟入里、入木三分。因此，这部作品—经问世，便广受欢迎，已被译成十几种语言。
     本书颠覆了人们通常对群体的认识，将群体的特点剖析得淋漓尽致，让人先是惊异，后是佩服。
     作者层层分析，逐步推进，明确指出个人一旦融入群体，他的个性便会被湮没，群体的思想便会占据绝对的统冶地位，而与此同时，群体的行为也会表现出排斥异议，极端化、情绪化及低智商化等特点，进而对社会产生破坏性的影响。
     《乌合之众——大众心理研究》是社会心理学领域中最具影响力的著作。深刻思考群体行为并意欲有所作为的人们都应该读读这本书。本书由古斯塔夫·勒庞著，戴光年译。",
                          "9787510407550","下架");

insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787509327296.png","中华人民共和国民法通则","中国法制出版社 编",
                          "中国法制出版社","2011-03-01",1,103,"平装","大32开",12,0.5,1023,
                          " (1)专业人员编写。本丛书皆由相关法律专家编写，内容明确，并力求语言通俗，使普通大众读者能更轻松地理解法律精神，掌握法律政策；
(2)法律适用提要。每本书都由相关法律专家撰写该法的适用提要，帮助读者对每一个法的背景、概况有更全面、深入的理解；
(3)重点法条注释。对重点法条进行条文注释，且每个条文都提炼出条文主旨，帮助读者准确理解法条内容；
(4)相关配套规定。书末附录一些较为重要的相关法律、法规和司法解释，使读者在使用中更为方便、实用。",
                          "9787509327296","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787200073102.png","爱弥儿","卢梭，方卿 著",
                          "北京出版社","2008-09-01",1,163,"平装","16开",23.8,0.66,234,
                          " 《爱弥儿，或论教育》，系法国资产阶级民主主义者、杰出的启蒙思想家卢梭(1712—1778)的重要著作。此书写于1757年，1762年第一次在荷兰的阿姆斯特丹出版。此书出版时，轰动了整个法国和西欧一些资产阶级国家，影响巨大。这部书不仅是卢梭论述资产阶级教育的专著，
                          而且是他阐发资产阶级社会政治思想的名著。",
                          "9787200073102","在架");

insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787802006348.png","英语快乐阅读（小学生5年级）","方洲 编",
                          "华语教学出版社","2009-08-01",1,152,"平装","16开",14.8,0.57,345,
                          " 阅读是学习英语的重要途径。它不仅是学好英语，积累词汇的基础，也是英语学习中其他技能的基础。阅读能帮助我们拓展视野，开阔思路，提高对英语学习的兴趣，为取得更高的语言成就铺平道路。
新课程标准规定小学阶段英语阅读量达到累计10—12万字。要达到这样的目标，没有足够的语言输入量是不行的。当学生有了一定的听、说基础之后，就需要不失时机地加强阅读学习。因为英语学习需要不断地复现，需要大量的输入来强化听、
说的语言材料并加以拓展。简单机械的复现和输入会使学生失去学习的兴趣，只有将所学的知识放到新的情景中，放到新的阅读活动中才能吸引学生，使学生保持持久的学习兴趣。为此，我们特编写了《小学英语快乐阅读》系列丛书。
本书所选短文内容幽默、新颖，难易程度适中，题型多样，涵盖了小学阅读理解中可能出现的各种题型。在文中的适当位置依据文章的内容配有精巧幽默的插图，以激发学生的阅读兴趣，帮助学生理解文章的内容。",
                          "9787802006348","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787560534541.png","研数学线性代数辅导讲义","李永乐　主编",
                          "西安交通大学出版社","2012-03-01",3,158,"平装","16开",25,0.70,323,
                          " 本书此次修订篇幅有所调整，除了补充、更换、编写了一些新题之外，针对同学们不太好理解或不大注意的地方，我们在评注中增加了较多的说明，同时也对全书的结构做了调整。
　　本书是为准备考研的学生复习线性代数而编写的一本辅导讲义，由编者近年来的辅导班笔记改写而成。全书共分六章及一个附录，每章均由知识结构网络图、基本内容与重要结论、典型例题分析选讲以及练习题精选四部分组成。本书在章节的顺序安排和内容取舍上与教材略有不同，
主要是为了方便同学们总结归纳以及更好地掌握知识间的相互渗透与转换。",
                          "9787560534541","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787800808821.png","新东方•四级词汇：词根+联想记忆法","俞敏洪 著",
                          "群言出版社","2008-09-01",1,433,"平装","32开",32,0.72,645,
                          " 词汇向来是学习英语的基石，更是应对所有考试的关键。词汇量的匮乏严重影响着一个人听、说、读、写等方面的能力。同样，不掌握一定数量的词汇，任何应试技巧都如隔靴搔痒，难有成效！
学习单词有多种方法，比如一日数词的日常积累，或者博览群书的大量阅读、亦或是买来词汇书记背的短期突破，当然对于应试来说，第三种方法无疑最直接、最有效。俗话说“好马配好鞍”，想背单词也需找好书。何谓“一本好的单词书”?简单说来就是具有行之有效的单词记忆方法、合理的单词安排以及实用有趣的内容。
本书旨在通过系统介绍“词根+联想”记忆法，让你在短时间内轻松记住四级单词，同时从新四级考试的考点出发，全方位解读核心词汇，让你对重点单词的用法了然于胸。",
                          "9787800808821","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787504572493.png","会展策划师：基础知识","吴杨 著 中国就业培训技术指导中心组织 编",
                          "中国劳动社会保障出版社","2008-07-01",1,125,"平装","16开",15,0.77,124,
                          " 本书由中国就业培训技术指导中心按照标准、教材、题库相衔接的原则组织编写。是国家职业技能鉴定推荐辅导用书。书中内容根据《国家职业标准·会展策划师》
                          （试行）要求编写，是会展策划师职业技能鉴定国家题库命题的直接依据。
　　本书介绍了各级别会展策划师应掌握的基础知识、涉及职业道德与政策法规、会展基础、会展策划基础、
会展营销基础、会展运营管理基础和会展文案写作基础等内容。",
                          "9787504572493","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787510036613.png","翻译的技巧","钱歌川 著",
                          "世界图书出版公司","2011-08-01",1,456,"平装","16开",56,0.72,345,
                          " 本书为钱歌川先生最经典的百科全书式的英语翻译技巧指南。全书编写严谨、结构合理、条理清晰，注重基础知识点的辨析与讲解，理论与实践相结合。一册在手，读者可以在牢固掌握英语语法和句型特点的基础上，游刃有余地学习翻译的技巧。
     全书共分为三编。第一编重点为中译英，系统梳理英语的各种句型，引领读者迅速进入英语语境；第二编重点为英译中，包含11项134条语法知识点，基本覆盖英语常见的惯用句和特殊表现法，令读者迅速摆脱洋径浜式英语；第三编主要为长篇的中译英和英译中材料，中文材料有浅近的白话和艰深的文言片段，英语材料全取近代人作品，主题多样，可使读者接触到英文表现的各种面貌。附录部分选取了16篇英美名家代表作的节译，可为读者打造直接阅读文学名著的基础。
     全书三编皆附有大量习题以供读者练习之用，书末提供了详细的习题答案。",
                          "9787510036613","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787030133083.png","晶体管电路设计（上）","（日本）铃木雅臣 著 周南生 译",
                          "科学出版社","2004-09-01",1,296,"平装","16开",29,0.66,123,
                          " 《晶体管电路设计（上）》作为上册主要内容有晶体管工作原理，放大电路的性能、设计与应用，射极跟随器的性能与应用电路，小型功率放大电路的设计与应用，功率放大器的设计与制作，共基极电路的性能、设计与应用，视频选择器的设计与制作，共射-共基电路的设计，负反馈放大电路的设计，直流稳定电源的设计与制作，差动放大电路的设计，运算放大电路的设计与制作，下册则共分15章，主要介绍FET、功率MOS、开关电源电路等。《晶体管电路设计（上）》面向实际需要，理论联系实际，
                          通过大量具体的实验，通俗易懂地介绍晶体管电路设计的基础知识。",
                          "9787030133083","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787506753753.png","制药企业设施设备GMP验证方法与实务","李歆　主编",
                          "中国医药科技出版社","2012-02-01",1,415,"平装","16开",55,0.80,34,
                          " 本书共十四章，结合大量示例，详细介绍了制药企业生产验证的基本知识，新修订的GMP对制药企业厂房与设施的要求，以及厂房和各类设施设备的验证或确认方法，其中包括厂房的设计确认，计量仪器仪表的校验确认，净化空调系统、纯化水注射用水系统、洁净室(区)空气臭氧消毒效果、过滤器和过滤系统．净化压缩空气系统的验证，以及制药机械(设备)、无菌制剂生产设备，口服固体和液体制剂生产设备、
                          中药丸剂生产设备、中药材前处理提取设备等的确认。",
                          "9787506753753","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787111187776.png","算法导论（原书第2版）","（美）科曼（Cormen,T.H.） 等著，潘金贵 等译",
                          "机械工业出版社","2006-09-01",1,754,"平装","16开",85,0.69,1342,
                          "本书深入浅出，全面地介绍了计算机算法。对每一个算法的分析既易于理解又十分有趣，并保持了数学严谨性。本书的设计目标全面，适用于多种用途。涵盖的内容有：算法在计算中的作用，概率分析和随机算法的介绍。本书专门讨论了线性规划，介绍了动态规划的两个应用，随机化和线性规划技术的近似算法等，还有有关递归求解、快速排序中用到的划分方法与期望线性时间顺序统计算法，以及对贪心算法元素的讨论。本书还介绍了对强连通子图算法正确性的证明，对哈密顿回路和子集求和问题的NP完全性的证明等内容。
                          全书提供了900多个练习题和思考题以及叙述较为详细的实例研究。",
                          "9787111187776","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787112059720.png","建筑施工手册（缩印本）","建筑施工手册（第四版）组 编",
                          "中国建筑工业出版社","2003-09-01",4,2429,"精装","16开",238,0.80,1342,
                          "《建筑施工手册》第四版缩印本是由《建筑施工手册》第四版1～5册缩印而成。全书共计36章内容。该书特点是：对近年来发展较快的施工技术内容作了大量补充；反映了建设部重点推广的新材料、新技术、新工艺；紧密结合近年来建筑材料、建筑结构设计、建筑安装施工质量验收等标准、规范、规程进行编写；根据国家施工质量验收规范要求，增加了建筑安装技术内容；该书由全国各地有丰富施工经验的专家、教授、高级工程师共同编写。本次修订突出了内容简洁、资料齐全、实用、查找方便、新技术信息含量高的特点。本书反映了21世纪最新的施工技术水平，书中囊括了许多最新的科研成果、内容系统、丰富、实用性强。是建筑工程技术人员的得力助手。
　　本书可供建筑施工工程技术人员、管理人员使用，也可供大专院校相关专业师生参考。",
                          "9787112059720","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787507528039.png","世界地理全知道（超值白金版）","任啸科 主编",
                          "华文出版社","2009-10-01",1,429,"平装","20开",29.8,0.50,356,
                          "本书为读者打开一扇快速认识和了解世界的窗口，是一部帮助读者快速学习和掌握世界地理知识的工具书。全书分为宇宙与地球、非洲、亚洲、大洋洲、美洲（南美洲和北美洲）、欧洲6个部分，全面系统地介绍了世界上200多个国家和属地的地理特征、自然资源、经济、习俗、交通、教育、旅游等诸多方面的知识，多角度解读世界自然和人文地理。并设置“国家概况”、“历史年表”等相关栏目，对世界地理知识加以延伸拓展，力图使繁杂的地理知识形成一个系统、科学的有机整体，方便读者学习掌握。同时，本书配入与文字内容相契合的精美图片，通过与版式的有机结合，让地理知识变得亲切，
                          陪伴读者开始一次愉快的读书之旅，轻松获取世界地理知识。",
                          "9787507528039","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787503836671.png","土壤学","孙向阳 主编",
                          "中国林业出版社","2005-01-01",1,360,"平装","16开",31,0.77,12,
                          "本书为普通高等教育“十五”国家级规划教材，主要适用于高等农林院校林学、水土保持、园林、园艺、环境、草业、资源环境与城乡规划管理、森林资源保护与游憩等本科专业的土壤学课程，同时也可作为生态、地理信息系统、生物等专业的教材与参考书。并可供从事农林、
                          生物、生态环境等相关领域的教学、科研和生产单位人员参考。",
                          "9787503836671","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787546400839.png","陈式太极拳简易24式","郭传光 著",
                          "成都时代出版社","2010-01-01",1,89,"平装","32开",22.8,0.48,456,
                          "《陈式太极拳简易24式》图文并茂，名师演示，通俗易懂，系统而详细地介绍了洪式太极拳的诞生及发展历史，拳法的精神和拳法的功效，以及它的基本手型、
                          主要手法、基本步型、主要步法、基本功，和最核心的洪式太极拳的简易24式拳谱。",
                          "9787546400839","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787117098519.png","人体解剖彩色图谱（第2版）","郭光文 等主编",
                          "人民卫生出版社","2008-04-01",2,264,"精装","16开",75,0.72,234,
                          "《人体解剖彩色图谱》第一版是我国自编的第一部供全国高等及中等医学院校、各医药卫生学习班和各医疗单位使用的彩色图谱。本图谱自1986年出版以来，迄今已逾20年，承蒙广大读者的支持，现已重印多次。在此期间，本图谱于1988年荣获国家新闻出版总署颁发的“第四届全国优秀科技图书一等奖”，1998年荣获人民卫生出版社颁发的“首届最佳双效益图书一等奖”。这些成就的取得，除中国医科大学、浙江医科大学、华西医科大学及大连医科大学的编绘者共同努力和团结协作外，亦受益于上海医科大学、上海第二医科大学、
                          北京医科大学、首都医科大学、同济医科大学、白求恩医科大学和哈尔滨医科",
                          "9787117098519","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787040072679.png","面向21世纪课程教材：海洋科学导论","冯士筰 等 著",
                          "高等教育出版社","2010-11-01",1,503,"平装","16开",39.8,0.80,76,
                          "《面向21世纪课程教材：海洋科学导论》被列入九五国家级重点教材。《面向21世纪课程教材：海洋科学导论》共分十二章，即绪论、地球系统与海底科学，海水的物理特性和世界大洋的层化结构，海水的化学组成和特性，海洋环流，海洋中的波动现象，潮汐，海洋与大气，海洋生物，海洋中的声、光传播及其应用，卫星海洋遥感、中国近海的区域海洋学。与以往的同类教材相比，新增了三章，即海洋与大气、卫星海洋遥感和中国海的区域海洋学；其他各传统分支学科的内容也有较多的更新。关于环境保护、污染治理与可持续发展等内容，虽未单独成章，但在有关章节中均有意强调了这方面的内容。
　　《面向21世纪课程教材：海洋科学导论》可作为海洋科学类本科学生及相近专业学生的基础课教材，亦可作为相近专业的教学参考用书，
对从事相近专业的科技人员或有关行业的管理人员，也有较大的参考价值。",
                          "9787040072679","在架");
insert into book (picture, bookname, author,publisher, publishdate, version, page, binding, 
                folio,marketprice, discount, inventory,bookdesc, 
                  isbn, offline)
                  values ("/upload/9787101059328.png","金刚经 心经 坛经","陈秋平，尚荣　译注",
                          "中华书局","2007-12-01",1,302,"平装","32开",16,0.63,145,
                          "《金刚经》、《心经》、《坛经》在佛教中的地位，不仅在中国佛教中体现着原典精神和独有的思想价值，从中国文化史的视野来看，它们的影响并且关涉到僧俗各界，渗透到社会文化的方方面面。因而如欲深入佛教研究或是欲对中国文化有进一步的了解，
                          此三部经是一个基础，是必须一读的经典。。",
                          "9787101059328","在架");

-- 向user表和secret表插入数据
insert into user(username, nickname, score, question, answer, registedate)
values ("641567179@qq.com","sanzi","1023","您高中的班主任是？","何老师", date(now()));
insert into secret(userid, password)
values(1,md5("123456"));

insert into user(username, nickname, score, question, answer, registedate)
values ("544921965@qq.com","北楼嗣音","1123","您的出生地","高州", "2009-03-12");
insert into secret(userid, password)
values(2,md5("234567"));

insert into user(username, nickname, score, question, answer, registedate)
values ("236497339@qq.com","曲线茬侧","983","您的出生地","温州", "2008-04-07");
insert into secret(userid, password)
values(3,md5("345678"));

insert into user(username, nickname, score, question, answer, registedate)
values ("1292740870@qq.com","pacman","2983","您的出生地","北海", "2005-02-24");
insert into secret(userid, password)
values(4,md5("4567893"));

insert into user(username, nickname, score, question, answer, registedate)
values ("1274892950@qq.com","wanran","83","我的名字","单小熙", "2012-02-12");
insert into secret(userid, password)
values(5,md5("56789012"));

insert into user(username, nickname, score, question, answer, registedate)
values ("290047285@qq.com","麦比乌斯","10883","我的名字","王启明", "2002-03-17");
insert into secret(userid, password)
values(6,md5("5678901sdsf2"));

insert into user(username, nickname, score, question, answer, registedate)
values ("317012583@qq.com","E.pHatAi.M","13883","我的名字","尹帆", "2003-12-30");
insert into secret(userid, password)
values(7,md5("34768gh2"));

insert into user(username, nickname, score, question, answer, registedate)
values ("549047626@qq.com","川","13883","我的名字","张盛川", "2011-10-01");
insert into secret(userid, password)
values(8,md5("347df68gt2"));

insert into user(username, nickname, score, question, answer, registedate)
values ("1037942090@qq.com","陈万忠","883","我的名字","陈万忠", "2009-10-21");
insert into secret(userid, password)
values(9,md5("qqfgh567"));

insert into user(username, nickname, score, question, answer, registedate)
values ("115551007@qq.com","swpeng","8833","我的名字","彭绍武", "2004-04-04");
insert into secret(userid, password)
values(10,md5("436556879"));

-- 向user_addr表插入数据
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("641567179@qq.com","时钰森","广东省广州市番禺区大学城","510000",null,"15018713530");
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("641567179@qq.com","时钰森","安徽省合肥市庐江县庐城镇","231500",null,"15018713530");
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("544921965@qq.com","刘光宗","广东省广州市番禺区大学城","510000",null,"15920551404");
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("544921965@qq.com","刘光宗","广东省广州市越秀区华南理工大学","510000",null,"15920551404");
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("236497339@qq.com","缪云彬","广东省广州市番禺区大学城","510000",null,"15920551434");
insert user_addr(USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM)
values("236497339@qq.com","缪云彬","浙江省温州市瑞安市安阳街道","325000",null,"15920551434");

-- 向book_cata表插入数据
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("文艺","1",0,"文学艺术");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("少儿","1",0,"少儿");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("人文社会","1",0,"人文社会");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("经济与管理","1",0,"经济与管理");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("生活与励志","1",0,"生活与励志");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("科技","1",0,"科技");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("教育","1",0,"教育");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("其它","1",0,"其它");
      
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("小说","2",1,"小说");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("文学","2",1,"文学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("青春文学","2",1,"青春文学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("传记","2",1,"传记");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("艺术","2",1,"艺术");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("动漫/幽默","2",1,"动漫/幽默");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("0—2","2",2,"0—2年龄段");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("3—6","2",2,"3—6年龄段");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("7—10","2",2,"7—10年龄段");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("11—14","2",2,"11—14年龄段");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("历史","2",3,"历史");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("哲学","2",3,"哲学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("国学","2",3,"国学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("宗教","2",3,"宗教");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("法律","2",3,"法律");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("心理学","2",3,"心理学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("社会科学","2",3,"社会科学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("古籍","2",3,"古籍");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("政治","2",3,"政治");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("军事","2",3,"军事");
      
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("经济","2",4,"经济");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("金融/投资","2",4,"金融/投资");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("市场营销","2",4,"市场营销");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("会计","2",4,"会计");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("创业","2",4,"创业");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("管理","2",4,"管理");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("两性","2",5,"两性");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("孕期","2",5,"孕期");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("育儿","2",5,"育儿");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("亲子关系","2",5,"亲子关系");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("保健","2",5,"保健");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("运动","2",5,"运动");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("美妆","2",5,"美妆");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("手工DIY","2",5,"手工DIY");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("美食","2",5,"美食");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("旅游","2",5,"旅游");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("休闲","2",5,"休闲");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("家庭家居","2",5,"家庭家居");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("风水占卜","2",5,"风水占卜");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("修养","2",5,"修养");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("成功","2",5,"成功");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("职场","2",5,"职场");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("沟通","2",5,"沟通");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("科普","2",6,"科普");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("建筑","2",6,"建筑");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("医学","2",6,"医学");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("农林","2",6,"农林");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("科学/自然","2",6,"科学/自然");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("计算机","2",6,"计算机");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("体育运动","2",6,"体育运动");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("通信","2",6,"通信");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("工程","2",6,"工程");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("教材","2",7,"教材");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("考试","2",7,"考试");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("外语学习","2",7,"外语学习");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("新闻出版","2",7,"新闻出版");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("语言文学","2",7,"语言文学");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("工具书","2",8,"工具书");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("期刊","2",8,"期刊");
insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("套装书","2",8,"套装书");

insert into book_catalog(catalogname,level,upID,catalogdesc)
      values("工业","2",6,"工程");
      
--向book_catalog_detail中插入数据
insert into book_catalog_detail(bookID,catalogID)
      values (1,12);
insert into book_catalog_detail(bookID,catalogID)
      values (2,9);
insert into book_catalog_detail(bookID,catalogID)
      values (3,10);
insert into book_catalog_detail(bookID,catalogID)
      values (4,11);
insert into book_catalog_detail(bookID,catalogID)
      values (5,13);
insert into book_catalog_detail(bookID,catalogID)
      values (6,14);
insert into book_catalog_detail(bookID,catalogID)
      values (7,19);
insert into book_catalog_detail(bookID,catalogID)
      values (8,28);
insert into book_catalog_detail(bookID,catalogID)
      values (9,27);
insert into book_catalog_detail(bookID,catalogID)
      values (10,20);
insert into book_catalog_detail(bookID,catalogID)
      values (11,22);
insert into book_catalog_detail(bookID,catalogID)
      values (12,21);
insert into book_catalog_detail(bookID,catalogID)
      values (13,24);
insert into book_catalog_detail(bookID,catalogID)
      values (14,23);
insert into book_catalog_detail(bookID,catalogID)
      values (15,25);
      
insert into book_catalog_detail(bookID,catalogID)
      values (16,61);
insert into book_catalog_detail(bookID,catalogID)
      values (17,62);
insert into book_catalog_detail(bookID,catalogID)
      values (18,62);
insert into book_catalog_detail(bookID,catalogID)
      values (19,64);
insert into book_catalog_detail(bookID,catalogID)
      values (20,65);
      
insert into book_catalog_detail(bookID,catalogID)
      values (21,60);
insert into book_catalog_detail(bookID,catalogID)
      values (22,69);
insert into book_catalog_detail(bookID,catalogID)
      values (23,57);
insert into book_catalog_detail(bookID,catalogID)
      values (24,53);
insert into book_catalog_detail(bookID,catalogID)
      values (25,52);
insert into book_catalog_detail(bookID,catalogID)
      values (26,55);
insert into book_catalog_detail(bookID,catalogID)
      values (27,58);
insert into book_catalog_detail(bookID,catalogID)
      values (28,54);
insert into book_catalog_detail(bookID,catalogID)
      values (29,56);
insert into book_catalog_detail(bookID,catalogID)
      values (30,26);

-- 向Book_order表插入数据
insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2011-03-23","0","0","0","641567179@qq.com","时钰森","广东省广州市番禺区大学城",
        "510000",null,"15018713530",125.0, 120);
        
insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2011-05-03","2","0","0","641567179@qq.com","时钰森","广东省广州市番禺区大学城",
        "510000",null,"15018713530",57.0, 50);
        
insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2011-05-24","3","0","0","544921965@qq.com","刘光宗","广东省广州市番禺区大学城",
        "510000",null,"15920551404",2345.5, 2500);

insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2011-07-01","0","0","0","544921965@qq.com","刘光宗","广东省广州市番禺区大学城",
        "510000",null,"15920551404",0.51, 0);

insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-01-01","0","0","0","236497339@qq.com","缪云彬","广东省广州市番禺区大学城",
        "510000",null,"15920551434",35.3, 40);

insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-01-07","0","0","0","236497339@qq.com","缪云彬","广东省广州市番禺区大学城",
        "510000",null,"15920551434",76.3, 80);
        
insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-02-15","4","0","0","236497339@qq.com","缪云彬","广东省广州市番禺区大学城",
        "510000",null,"15920551434",76.3, 100);

insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-03-15","4","0","0","544921965@qq.com","刘光宗","广东省广州市番禺区大学城",
        "510000",null,"15920551404",176.3, 200);

insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-06-15","4","0","0","236497339@qq.com","缪云彬","广东省广州市番禺区大学城",
        "510000",null,"15920551434",55.3, 60);
        
insert into book_order(ORDERDATE,ORDERSTATE,PAYMENT,DELIVERYWAY,
                      USERNAME,RECEIVERNAME,ADDRDETAIL,ZIPCODE,PHONENUM,MOBILENUM,TOTALPRICE, SCORE)
values ("2012-03-23","2","0","0","641567179@qq.com","时钰森","广东省广州市番禺区大学城",
        "510000",null,"15018713530",75.0, 100);
        
-- 向Order_detail表插入数据
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(1,1,9787538296846,"曾国藩和他的湘军",32.80,0.69,2);

insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(1,20,9787510036613,"翻译的技巧",56.0,0.72,1);

insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(1,30,9787101059328,"金刚经 心经 坛经",16.00,0.63,3);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(2,25,9787507528039,"世界地理全知道（超值白金版）",29.80,0.5,1);

insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(3,1,9787538296846,"曾国藩和他的湘军",32.80,0.69,20);

insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(3,2,9787539949789,"银河帝国2：基地与帝国",29.90,0.69,1);

insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(3,8,9787532544400,"孙子兵法",20.00,0.65,12);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(4,8,9787532544400,"孙子兵法",20.00,0.65,2);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(5,30,9787101059328,"金刚经 心经 坛经",16.00,0.63,3);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(6,4,9787539936680,"那些回不去的年少时光：终场",23.80,0.63,1);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(7,10,9787505721456,"东方哲学简史（珍藏插图版）",35.00,0.36,1);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(8,10,9787505721456,"东方哲学简史（珍藏插图版）",35.00,0.36,1);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(8,11,9787549500444,"古犹太教",50.00,0.66,1);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(9,6,9787535838858,"游园惊梦",24.00,0.5,2);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(10,18,9787800808821,"新东方•四级词汇：词根+联想记忆法",32.00,0.72,2);
    
insert into order_detail(ORDERID,BOOKID,ISBN,BOOKNAME,MARKETPRICE,DISCOUNT,AMOUNT)
    values(10,27,9787546400839,"陈式太极拳简易24式",22.80,0.48,2);
    


