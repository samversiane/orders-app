# Etapa de Construção
FROM node:20 as builder

# Define o diretório de trabalho no container
WORKDIR /app

# Instala as dependências do Angular
COPY package*.json ./
RUN npm install

# Copia todos os arquivos da aplicação para o container
COPY . .

# Expoe a porta 4200, usada pelo Angular
EXPOSE 4200

# Comando para rodar o servidor do Angular (modo desenvolvimento)
CMD ["npm", "run", "start-docker"]
