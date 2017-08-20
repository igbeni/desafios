package br.com.igbeni.crawler.model;

import org.jetbrains.annotations.NotNull;

/**
 * Model for the SubReddit info.
 *
 * @author Iggor Alves
 */
public class SubReddit implements Comparable<SubReddit> {
    private String subreddit;
    private String threadTitle;
    private String threadLink;
    private String threadAuthor;
    private String threadAuthorLink;
    private long upVotes;

    /**
     *
     */
    private SubReddit() {
    }

    /**
     * @return
     */
    @NotNull
    @Override
    public String toString() {

        return "Title: " + threadTitle +
                '\n' +
                "Author: " + threadAuthor +
                '\n' +
                "Subreddit: " + subreddit +
                '\n' +
                "Link: " + threadLink +
                '\n' +
                "Author link: " + threadAuthorLink +
                '\n' +
                "Up votes: " + upVotes +
                '\n';
    }

    /**
     * @param subReddit
     * @return
     */
    @Override
    public int compareTo(@NotNull SubReddit subReddit) {
        return upVotes >= subReddit.upVotes ? -1 : 1;
    }

    /**
     *
     */
    public static final class SubRedditBuilder {
        private String subreddit;
        private String threadTitle;
        private String threadLink;
        private String threadAuthor;
        private String threadAuthorLink;
        private long upVotes;

        /**
         *
         */
        private SubRedditBuilder() {
        }

        /**
         * @return
         */
        public static SubRedditBuilder create() {
            return new SubRedditBuilder();
        }

        /**
         * @param subreddit
         * @return
         */
        @NotNull
        public SubRedditBuilder withSubreddit(String subreddit) {
            this.subreddit = subreddit;
            return this;
        }

        @NotNull
        public SubRedditBuilder withThreadTitle(String threadTitle) {
            this.threadTitle = threadTitle;
            return this;
        }

        /**
         * @param threadLink
         * @return
         */
        @NotNull
        public SubRedditBuilder withThreadLink(String threadLink) {
            this.threadLink = threadLink;
            return this;
        }

        /**
         * @param threadAuthor
         * @return
         */
        @NotNull
        public SubRedditBuilder withThreadAuthor(String threadAuthor) {
            this.threadAuthor = threadAuthor;
            return this;
        }

        /**
         * @param threadAuthorLink
         * @return
         */
        @NotNull
        public SubRedditBuilder withThreadAuthorLink(String threadAuthorLink) {
            this.threadAuthorLink = threadAuthorLink;
            return this;
        }

        /**
         * @param upVotes
         * @return
         */
        @NotNull
        public SubRedditBuilder withUpVotes(long upVotes) {
            this.upVotes = upVotes;
            return this;
        }

        /**
         * @return
         */
        @NotNull
        public SubReddit build() {
            SubReddit subReddit = new SubReddit();
            subReddit.subreddit = this.subreddit;
            subReddit.threadTitle = this.threadTitle;
            subReddit.threadLink = this.threadLink;
            subReddit.threadAuthor = this.threadAuthor;
            subReddit.threadAuthorLink = this.threadAuthorLink;
            subReddit.upVotes = this.upVotes;
            return subReddit;
        }
    }
}
