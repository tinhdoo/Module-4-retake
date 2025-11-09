package com.example.football.aspect;

import com.example.football.entity.Player;
import com.example.football.repository.IPlayerRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Aspect
@Component
@RequiredArgsConstructor
public class PlayerStatusLogger {

    private final IPlayerRepository playerRepository;
    private static final String LOG_FILE = "logs/player-status-log.txt";

    @AfterReturning(
            pointcut = "execution(* com.example.football.service.PlayerService.save(..))",
            returning = "result")
    public void logAfterAddPlayer(JoinPoint joinPoint, Object result) {
        if (!(result instanceof Player player)) return;

        String logMessage = String.format("[%s] Thêm mới cầu thủ: %s%n",
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")),
                player.getName());

        writeLogToFile(logMessage);
    }

    private void writeLogToFile(String logMessage) {
        try {
            File logDir = new File("logs");
            if (!logDir.exists()) logDir.mkdirs();

            try (FileWriter writer = new FileWriter(LOG_FILE, true)) {
                writer.write(logMessage);
            }
        } catch (IOException e) {
            System.err.println(" Không thể ghi log: " + e.getMessage());
        }
    }
}
