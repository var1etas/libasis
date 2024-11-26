package ru.filatov.libasis.service;

import org.springframework.stereotype.Service;
import ru.filatov.libasis.entity.BookEntity;
import ru.filatov.libasis.entity.UserEntity;

import java.util.List;

@Service
public class HtmlConverter {

    public StringBuilder generatePage(StringBuilder users, StringBuilder books, long userTimer, long bookTimer, long reportTimer) {
        StringBuilder html = new StringBuilder();
        addHeader(html);

        addTableHeader(html);
        html.append(users);
        addTableFooter(html);

        addTableHeader(html);
        html.append(books);
        addTableFooter(html);

        addTimerTable(html, userTimer, bookTimer, reportTimer);

        addFooter(html);

        return html;
    }

    private void addHeader(StringBuilder report){
        report.append("<!DOCTYPE html>\n" +
                "<html xmlns:th=\"http://www.thymeleaf.org\">\n" +
                "<head>\n" +
                "    <title>User</title>\n" +
                "</head>\n" +
                "<body>\n");
    }

    private void addFooter(StringBuilder html){
        html.append("</body>\n" +
                "</html>");
    }

    private void addTableHeader(StringBuilder html){
        html.append("<table border=\"1\">");
    }

    private void addTableFooter(StringBuilder html){
        html.append("</table>");
    }

    private void addTimerTable(StringBuilder html, long userTimer, long bookTimer, long reportTimer){
        html.append("<table border=\"1\">\n");
        html.append("<tr>\n"+
                "<td>Время на получение пользователей=" + userTimer + "мс</td>\n" + "</tr>");
        html.append("<tr>\n"+
                "<td>Время на получение всех книг=" + bookTimer + "мс</td>\n" + "</tr>");
        html.append("<tr>\n"+
                "<td>Время на формирование отчета=" + reportTimer + "мс</td>\n" + "</tr>");
        html.append("</table>");
    }
}
