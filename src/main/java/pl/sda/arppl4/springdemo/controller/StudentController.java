package pl.sda.arppl4.springdemo.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.sda.arppl4.springdemo.model.Student;
import pl.sda.arppl4.springdemo.service.StudentService;

import java.util.List;

@Slf4j
@RequestMapping("/api/student")
@RestController
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping()
    public List<Student> studentList() {
        log.info("Wywołano metodę studentList");
        List<Student> studentList = studentService.findAll();
        return studentList;
    }

    @ApiOperation(value = "znajdzStudenta", notes = "Ten endpoint pozwala na znajdowanie studentów po ich identyfikatorach w bazie danych")
    @ApiResponses(value = {
            @ApiResponse(code =200, message = "Ten kod oznacza sukces operacji i poprawne załadowanie obiektu z tabeli /student/ w bazie."),
            @ApiResponse(code =400, message = "Ten kod oznacza źle wpisaną wartość lub typ parametru"),
            @ApiResponse(code =401, message = "Ten kod oznacza brak uprawnień do wykonywania tej operacji"),
            @ApiResponse(code =403, message = "Ten kod oznacza dostęp zabroniony."),
            @ApiResponse(code =500, message = "Ten kod oznacza błąd serwera")
    })
    @GetMapping("/{identifier}")
    public Student findStudent(@ApiParam(name = "Identyfikator studenta", example = "1", type = "long", required = true) @PathVariable(name = "identifier") Long studentId) {
        log.info("Wywołano metodę findStudent" + studentId);

        return studentService.findById(studentId);
    }

    @GetMapping("/find")
    public Student findStudentById(@RequestParam Long studentId) {
        log.info("Wywołano metodę findStudentById" + studentId);
        return studentService.findById(studentId);
    }

    @DeleteMapping("/{identifier}")
    public void deleteStudent(@PathVariable(name = "identifier") Long studentId) {
        log.info("Wywołano metodę deleteStudent" + studentId);
        studentService.deleteById(studentId);
    }

    @GetMapping("/findByName")
    public List<Student> findStudentByName(@RequestParam(name = "name") String searchedName) {
        log.info("Wywołano metodę findStudentByName " + searchedName);
        return studentService.findAllByNameContaining(searchedName);
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody Student student) {
        log.info("Wywołano metodę createStudent: " + student);
        studentService.save(student);
    }
}
