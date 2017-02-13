# dropper

Os dados multimídia são amplamente utilizados pelos usuários e organizações atuais, porém, sua estrutura faz com que sua manipulação tenha um impacto grande no desempenho dos sistemas e aplicações, especialmente as que fazem uso de banco de dados para o gerenciamento das informações. Desse modo, este trabalho apresenta um sistema para armazenamento e compartilhamento de arquivos multimídia em um ambiente distribuído contruído com sistemas embarcados de baixo custo, utilizando um método de inserção e recuperação de dados não estruturados diretamente em uma base de dados replicada, através de cadeias binárias, garantindo assim que o SGBD tenha total controle sobre a integridade e a segurança destes arquivos. Para o desenvolvimento da aplicação foram utilizadas as linguagens Java e Lua, já para o desenvolvimento e implementação do sistema de banco de dados foi utilizado o SGBD PostgreSQL, em conjunto com o middleware pgpool-II em um sistema Linux Fedora, responsável por replicar os dados e balancear a carga dos servidores para maior eficiência em ambientes multiusuários. Os resultados mostraram que a aplicação obteve êxito em armazenar, recuperar e compartilhar os arquivos multimídia com eficiência, porém, consumindo elevados recursos de memória em arquivos extensos. Os sistemas embarcados em conjunto com o middleware conseguiram prover um ambiente distribuído eficiente, replicando a base de dados e aumentando a eficiência no acesso de vários usuários simultâneos.

#Preview

![alt tag](https://cloud.githubusercontent.com/assets/22321039/22889794/8164f6b6-f1f1-11e6-8600-d4daa1c629cb.png)
![alt tag](https://cloud.githubusercontent.com/assets/22321039/22889797/816807f2-f1f1-11e6-8129-e49bd5bf2f7f.png)
![alt tag](https://cloud.githubusercontent.com/assets/22321039/22889795/81665812-f1f1-11e6-90b6-82586627c5bb.png)
![alt tag](https://cloud.githubusercontent.com/assets/22321039/22889796/81676cde-f1f1-11e6-8590-9ad66494026b.png)
![alt tag](https://cloud.githubusercontent.com/assets/22321039/22889798/81687e12-f1f1-11e6-8476-e07c525caebe.png)
