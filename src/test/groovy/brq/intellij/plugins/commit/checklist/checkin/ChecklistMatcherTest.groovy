package brq.intellij.plugins.commit.checklist.checkin

import brq.intellij.plugins.commit.checklist.settings.MessageItem
import spock.lang.Specification
import spock.lang.Unroll

class ChecklistMatcherTest extends Specification {

    @Unroll
    def "should get checklist items matching files - different checklists: #checklist"() {
        given:
        def files = testFiles()

        when:
        def result = ChecklistMatcher.getMatchedChecklist(checklist, files)

        then:
        result == expected

        where:
        checklist                                                 || expected
        []                                                        || []
        [item(" ")]                                               || []
        [item("All")]                                             || ["All"]
        [item("ts", "*.ts")]                                      || []
        [item("java", "*.java"), item("*ts", "*.ts")]             || ["java"]
        [item("java", "*.java"), item("kt", "*.kt"), item("All")] || ["java", "kt", "All"]
        [item("db/migration", "*db/migration/*")]                 || ["db/migration"]
    }

    @Unroll
    def "should get checklist items matching files - different files: #files"() {
        given:
        def checklist = testChecklist()

        when:
        def result = ChecklistMatcher.getMatchedChecklist(checklist, files)

        then:
        result == expected

        where:
        files                                         || expected
        []                                            || []
        [file("src/Test.txt")]                        || ["All"]
        [file("src/brq/test-package/Test.kt")]        || ["All", "kt"]
        [file("src/brq/Test.java")]                   || ["All", "java"]
        [file("src/brq/test-package/Test.java")]      || ["All", "java", "java-pckg"]
        [file("src/Test.js")]                         || ["All", "js"]
        [file("src/brq/db/migration/1/migration.js")] || ["All", "db/migration", "db-1", "js"]
        [file("src/brq/db/migration/2/migration.js")] || ["All", "db/migration", "js"]
    }

    def testFiles() {
        return [
                file("src/brq/test-package/Class.java"),
                file("src/brq/test-package/Class.kt"),
                file("src/brq/db/migration/1/migration.js"),
                file("src/brq/db/migration/2/migration.js"),
                file("src/brq/db/migration/10/migration.js"),
                file("src/brq/test.js")
        ]
    }

    def file(def path) {
        return new File(path)
    }

    def testChecklist() {
        return [
                item("All"),
                item("db/migration", "*db/migration/*"),
                item("java", "*.java"),
                item("kt", "*.kt"),
                item("java-pckg", "*test-package/*.java"),
                item("db-1", "*db*1*"),
                item("js", "*.js"),
        ]
    }

    def item(def item, def mask = "*") {
        return new MessageItem(item, mask)
    }
}
