# MineSky Events

Plugin principal dos eventos automáticos e manuais do servidor.

## Comandos de Eventos

### Eventos Automáticos

- `/evento iniciar (EVENTO)`  
  Inicia um evento automático.

- `/evento finalizar (EVENTO)`  
  Finaliza imediatamente um evento automático. Todos os eventos automáticos são finalizados automaticamente ao término, exceto os anunciados, que devem ser finalizados manualmente por um Event Maker.

- `/evento anunciar (EVENTOS_AGENDADOS) (MAPA_AGENDADO ex: 1)`  
  Anuncia um evento automático e agendado.

- `/evento force (start/skip)`
    - `start`: Inicia o evento com menos de 4 jogadores.
    - `skip`: Pula a contagem regressiva de 3 minutos.

- `/evento set (EVENTO)`  
  Define os locais dos eventos automáticos e o spawn dos eventos agendados. (Somente para o Event Manager)

- `/evento kick (PLAYER)`  
  Expulsa um jogador do servidor de eventos.

- `/evento blacklist (adicionar/remover) (PLAYER) [IP]`  
  Adiciona ou remove um jogador da blacklist (por IP ou não).

- `/evento entrar`  
  Permite que um jogador participe do evento.

### Outros Comandos

- `/msne money (PLAYER) (VALOR)`  
  Define um valor de dinheiro para um jogador.

- `/msne clear`  
  Apaga o inventário de todos os jogadores online.

