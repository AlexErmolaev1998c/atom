package ru.atom.chat.server;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Controller
@RequestMapping("chat")
public class ChatController {
    private Queue<String> messages = new ConcurrentLinkedQueue<>();
    private Map<String, String> usersOnline = new ConcurrentHashMap<>();

    /**
     * curl -X POST -i localhost:8080/chat/login -d "name=I_AM_STUPID"
     */
    @RequestMapping(
            path = "login",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> login(@RequestParam("name") String name) {
        if (name.length() < 1) {
            return ResponseEntity.badRequest().body("Too short name, sorry :(");
        }
        if (name.length() > 20) {
            return ResponseEntity.badRequest().body("Too long name, sorry :(");
        }
        if (usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("Already logged in :(");
        }
        usersOnline.put(name, name);
        messages.add("[" + name + "] logged in");
        return ResponseEntity.ok().build();
    }

    /**
     * curl -i localhost:8080/chat/online
     */
    @RequestMapping(
            path = "online",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity online() {
        String responseBody = String.join("\n", usersOnline.keySet()
                .stream().sorted().collect(Collectors.toList()));
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl -X POST -i localhost:8080/chat/logout -d "name=I_AM_STUPID"
     */
    @RequestMapping(
            path = "logout",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> logout(@RequestParam("name") String name) {
        if (!usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("This user didn't log in :c");
        } else {
            usersOnline.remove(name);
            messages.add("[" + name + "] logged out");
            return ResponseEntity.ok().build();
        }
    }

    /**
     * curl -X POST -i localhost:8080/chat/say -d "name=I_AM_STUPID&msg=Hello everyone in this chat"
     */
    @RequestMapping(
            path = "say",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> say(@RequestParam("name") String name, @RequestParam("msg") String msg) {
        if (!usersOnline.containsKey(name)) {
            return ResponseEntity.badRequest().body("This user didn't log in :c");
        } else {
            messages.add("[" + name + "] " + msg);
            return ResponseEntity.ok().build();
        }
    }


    /**
     * curl -i localhost:8080/chat/chat
     */
    @RequestMapping(
            path = "chat",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity chat() {
        String responseBody = String.join("\n", messages.stream().collect(Collectors.toList()));
        return ResponseEntity.ok(responseBody);
    }

    /**
     * curl -i localhost:8080/chat/userWordsHistory?name=I_AM_STUPID
     */
    @RequestMapping(
            path = "userWordsHistory",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> userWordsHistory(@RequestParam("name") String name) {
        String responseBody = String.join("\n", messages.stream().filter(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                if (s.startsWith("[" + name + "]")) {
                    return true;
                } else {
                    return false;
                }
            }
        }).collect(Collectors.toList()));
        return ResponseEntity.ok(responseBody);
    }

    @RequestMapping(
            path = "rename",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> rename(@RequestParam("oldName") String oldName,
                                         @RequestParam("newName") String newName) {
        if (!usersOnline.containsKey(oldName)) {
            return ResponseEntity.badRequest().body("This user didn't log in :c");
        } else {
            usersOnline.remove(oldName);
            usersOnline.put(newName, newName);
            messages.add("[" + oldName + "] " + "now has the new name: " + "[" + newName + "]");
            return ResponseEntity.ok().build();
        }
    }
}
